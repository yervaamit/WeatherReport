package ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ui.utils.SeleniumUtils;

public class HomePage {

    private WebDriver driver;

    @FindBy (className = "topnavmore")
    private WebElement topNavMore;
    @FindBy (xpath = "//div[@class='topnav_cont']//a[contains(text(), 'WEATHER')]")
    private WebElement weatherLink;

    @FindBy (xpath = "//div[@class='neweleccont ntopnav_wrap2']")
    private WebElement topNav2;


    public HomePage(WebDriver prevDriver){
        driver = prevDriver;
        PageFactory.initElements(driver, this);
    }

    public void showMoreMenu()  {
        try{
            Thread.sleep(4000);
            SeleniumUtils.performClick(driver, topNavMore);
            topNavMore.click();
        }catch (InterruptedException e){
        }

    }

    public void clickTopNav2(){
        try {
            Thread.sleep(4000);
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].setAttribute('style', 'position: static; padding-top: 0px; display: block;')", topNav2);
        }catch (InterruptedException e){
        }
    }

    public void clickWeatherTopNav(){
//        JavascriptExecutor js = (JavascriptExecutor) driver;
//        js.executeScript("arguments[0].setAttribute('style', 'position: static; padding-top: 0px; display: block;')", topNav2);
        SeleniumUtils.performClick(driver, weatherLink);
    }

}
