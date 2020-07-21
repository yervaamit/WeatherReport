package ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import ui.utils.SeleniumUtils;

import java.util.logging.Level;
import java.util.logging.Logger;


public class NDTVWeatherPage {

    private WebDriver driver;
    private final Logger logger = Logger.getLogger("NDTVWeatherTest");

    @FindBy(id = "searchBox")
    private WebElement citySearchBox;
    @FindBy (xpath = "//div[contains(@class, 'leaflet-popup-content-wrapper')]//span[4]/b")
    private WebElement cityTempInfoPopup;
    @FindBy (xpath = "//div[@class='comment_cont']")
    private WebElement citySelector;
    @FindBy (xpath = "//*[@id='map_canvas']")
    public WebElement mapCanvas;

    @FindBy (xpath = "//div[@class='leaflet-pane leaflet-map-pane']")
    public WebElement parentConvas1;
    @FindBy (xpath = "//div[@class='leaflet-pane leaflet-marker-pane']")
    public WebElement parentConvas2;


    public NDTVWeatherPage(WebDriver prevDriver){
        driver = prevDriver;
        PageFactory.initElements(driver, this);
    }

    public void pinCity(String city){
        boolean status = isCitySelected(city);
        if(!status){
            driver.findElement(By.xpath("//label[@for='" + city + "']/input")).click();
        }
    }

    public WebElement getCitySelector() {
        return citySelector;
    }

    public boolean isCitySelected(String city){
        try{
            WebElement cityCheckbox = driver.findElement(By.xpath("//label[@for='" + city + "']/input"));
            String checkBoxStatus = cityCheckbox.getAttribute("checked");
            return Boolean.valueOf(checkBoxStatus);
        }
        catch (NullPointerException e){
            return false;
        }
    }

    public void openWeatherInfoPopup(String city){
        WebElement cityInMap = driver.findElement(By.xpath("//div[@title='" + city + "']"));
        SeleniumUtils.performClick(driver, cityInMap);
    }

    public int getTempInDegrees(){
        try{
            return Integer.parseInt(cityTempInfoPopup.getText().split(":")[1].trim());
        } catch (Exception e){
            logger.log(Level.INFO, "Something gone wrong while reading temperature data.");
            return -274;
        }
    }

}
