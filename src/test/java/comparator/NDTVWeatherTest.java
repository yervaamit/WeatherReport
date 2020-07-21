package comparator;

import api.APIResponse;
import io.restassured.path.json.JsonPath;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ui.pages.*;
import ui.BaseTest;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class NDTVWeatherTest extends BaseTest {

    private WebDriver driver;
    private WebDriverWait driverWait;

    @BeforeClass(alwaysRun = true)
    public void initialize() {
        driver = getDriver();
        driverWait = getWebDriverWait();
    }

    @Test
    public void testNew() {
        String city = getValue("City");

        HomePage homePage = new HomePage(driver);
        NDTVWeatherPage weatherPage = new NDTVWeatherPage(driver);

        homePage.clickTopNav2();
        homePage.clickWeatherTopNav();
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        driverWait.until(ExpectedConditions.visibilityOf(weatherPage.parentConvas1));
        driverWait.until(ExpectedConditions.visibilityOf(weatherPage.parentConvas2));
        weatherPage.pinCity(city);
        weatherPage.openWeatherInfoPopup(city);
        int temparatureFromNDTV = weatherPage.getTempInDegrees();
//        String humidityFromNDTV = weatherPage.getHumidity();

        System.out.println("Temparature reported by NDTV: " + temparatureFromNDTV);

        Map<String, String> queryParameters = new HashMap<String, String>();
        queryParameters.put("q", city);
        queryParameters.put("appid", getValue("AppId"));
        APIResponse apiResponse = new APIResponse();
        JsonPath  res = apiResponse.getResponse("/data/2.5/weather", "", queryParameters, "application/json",
                null, APIResponse.RequestMethodType.POST).jsonPath();

        int temp = (int)  (Double.parseDouble(res.getMap("main").get("temp").toString()) - 273.15); //API returns temperature in Kelvin
        System.out.println("Temparature reported by API: " + temp);

        int upparBoundAPI = temp + temp * Integer.valueOf(getValue("VariancePercent")) / 100;
        int lowerBoundAPI = temp - temp * Integer.valueOf(getValue("VariancePercent")) / 100;

        Assert.assertTrue(temparatureFromNDTV < upparBoundAPI && temparatureFromNDTV > lowerBoundAPI, "Temperatures are not in sync");

    }
}