package ui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TShirts {

    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(xpath = "//div[@class='product-image-container']//img")
    private WebElement fadedShortSleeveTShirt;
    @FindBy(xpath = "//a[@class='quick-view']")
    private WebElement quickViewFadedTShirt;
    @FindBy(xpath = "//p[@id='quantity_wanted_p']/a[2]")
    private WebElement addQuantity;
    @FindBy(xpath = "//p[@id='add_to_cart']/button")
    private WebElement addToCart;
    @FindBy(xpath = "//*[@title='Proceed to checkout']")
    private WebElement proceedToCheckout;

    @FindBy(tagName = "iframe")
    private WebElement quickViewFrame;

    public TShirts(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 10);
        PageFactory.initElements(driver, this);
    }

    public void quickViewFadedTShirt() {
        Actions actions = new Actions(driver);
        actions.moveToElement(fadedShortSleeveTShirt).perform();
        quickViewFadedTShirt.click();
    }

    public void increaseQuantity() {
        addQuantity.click();
    }

    public void addToCart() {
        addToCart.click();
    }

    public void clickProceedToCheckout() {
        proceedToCheckout.click();
    }

    public void switchFrame() {
        driver.switchTo().frame(quickViewFrame);
    }

    public void switchToParentFrame() {
        driver.switchTo().parentFrame();
        driver.switchTo().defaultContent();
    }

}
