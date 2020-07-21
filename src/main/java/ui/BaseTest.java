package ui;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
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

    public WebDriver getDriver() {
        setDriver();
        webDriver.manage().window().maximize();
        webDriver.get(getUrl());
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

}

