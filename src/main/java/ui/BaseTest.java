package ui;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeSuite;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BaseTest {
    private static BaseTest instance = null;
    public static WebDriver webDriver;
    public static WebDriverWait webDriverWait;
    private final static Logger logger = Logger.getLogger("BaseTest");
    private static Properties prop;
    private static ChromeOptions chromeOptions = new ChromeOptions();

    @BeforeSuite(alwaysRun = true)
    public void beforeSuite() {
        loadPropertyFile("./config.properties");
    }

    public static BaseTest getInstance() {
        if (instance == null) {
            instance = new BaseTest();
        }
        return instance;
    }

    private void setDriver(){
        WebDriverManager.chromedriver().setup();
        chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--incognito");
        chromeOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        webDriver=new ChromeDriver(chromeOptions);
        webDriverWait = new WebDriverWait(webDriver, 10);
    }

    public WebDriver getDriver(String url) {
        setDriver();
        webDriver.manage().window().maximize();
        webDriver.get(url);
        return webDriver;
    }
    public  WebDriverWait getWebDriverWait(){
        return webDriverWait;
    }

    private static void loadPropertyFile(String propertyFile) {
        FileInputStream fis;
        prop = new Properties();
        try {
            fis = new FileInputStream(propertyFile);
            prop.load(fis);
        } catch (java.io.IOException e) {
            logger.log(Level.INFO, "Could not load Property File : " + e.getMessage());
        }
    }

    public void scrollDownByPixels(int pixels) throws InterruptedException {
        Thread.sleep(2000);
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        js.executeScript("window.scrollBy(0," + pixels +")");
    }

    private static String getUrl()
    {
        return getValue("URL");
    }

    public void closeAndQuitDriver(){
        if (webDriver != null){
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            logger.log(Level.INFO, "Quiting driver of : "+stackTraceElements[2].getClassName());
            webDriver.close();
            webDriver.quit();
        }
    }
    public static String getValue(String key){
        return prop.getProperty(key);
    }

    @AfterClass(alwaysRun = true)
    public void afterClass() {
        if (webDriver != null){
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            logger.log(Level.INFO, "Quiting driver of : "+stackTraceElements[2].getClassName());
            webDriver.close();
            webDriver.quit();
        }
    }

    public void waitForElementToBeVisible(By locator) {
        waitForPageToLoadCompletely();
        try {
            webDriverWait.until(ExpectedConditions.presenceOfElementLocated(locator));
            webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            webDriverWait.until(ExpectedConditions.elementToBeClickable(locator));
        } catch (TimeoutException | NoSuchElementException | StaleElementReferenceException se) {
            waitForSomeTime(3);
            webDriverWait.until(ExpectedConditions.presenceOfElementLocated(locator));
            webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            webDriverWait.until(ExpectedConditions.elementToBeClickable(locator));
        }
    }
    public static void waitForPageToLoadCompletely() {
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*")));
    }

    public static void waitForSomeTime(int seconds) {
        try {
            waitForPageToLoadCompletely();
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

}

