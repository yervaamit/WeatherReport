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
        driver = getDriver("https://www.ndtv.com/");
        driverWait = getWebDriverWait();
    }

    @Test
    public void NDTVWeatherInfoValidator() {
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
        int humidityFromNDTV = weatherPage.getHumidity();

        System.out.println("Temparature reported by NDTV: " + temparatureFromNDTV);
        Map<String, String> queryParameters = new HashMap<String, String>();
        queryParameters.put("q", city);
        queryParameters.put("appid", getValue("AppId"));
        APIResponse apiResponse = new APIResponse();
        JsonPath  res = apiResponse.getResponse("/data/2.5/weather", "", queryParameters, "application/json",
                null, APIResponse.RequestMethodType.POST).jsonPath();

        int apiTemp = (int)  (Double.parseDouble(res.getMap("main").get("temp").toString()) - 273.15); //API returns temperature in Kelvin
        int apiHumidity = Integer.parseInt(res.getMap("main").get("humidity").toString()); //API returns temperature in Kelvin
        System.out.println("Temparature reported by API: " + apiTemp);

//        closeAndQuitDriver();

        int upparBoundAPI = apiTemp + apiTemp * Integer.valueOf(getValue("VariancePercent")) / 100;
        int lowerBoundAPI = apiTemp - apiTemp * Integer.valueOf(getValue("VariancePercent")) / 100;
        Assert.assertTrue(temparatureFromNDTV < upparBoundAPI && temparatureFromNDTV > lowerBoundAPI,
                "Temperature reported by NDTC is not as reported by API.");

        int upparBoundHumidity = apiHumidity + apiHumidity * Integer.valueOf(getValue("VariancePercent")) / 100;
        int lowerBoundHumidity = apiHumidity - apiHumidity * Integer.valueOf(getValue("VariancePercent")) / 100;
        Assert.assertTrue(humidityFromNDTV < upparBoundHumidity && humidityFromNDTV > lowerBoundHumidity,
                "NDTV reported Humidity is not as reported by API.");

    }
}
