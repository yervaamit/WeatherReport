package ui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CartCheckout {
    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(id = "total_price") public WebElement totalPrice;
    @FindBy(className = "bankwire") private WebElement payByWire;
    @FindBy(id = "uniform-cgv") private WebElement aggreTermsOfService;
    @FindBy(name = "processCarrier") private WebElement proceedToPayment;
    @FindBy(name = "processAddress") private WebElement proceedToShipping;
    @FindBy(xpath = "//a[@title='Back to orders']") private WebElement backToOrders;
    @FindBy(xpath = "//span[@class='price'][1]") private WebElement totalPriceOrdered;
    @FindBy(xpath = "//*[@id='cart_navigation']//button") private WebElement iConfirmOrder;
    @FindBy(xpath = "//p/*[@title='Proceed to checkout']") private WebElement proceedToCheckout;


    public CartCheckout(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 10);
        PageFactory.initElements(driver, this);
    }
    public void clickProceedToCheckout(){
        proceedToCheckout.click();
    }

    public void checkAggreTermsOfService(){
        aggreTermsOfService.click();
    }

    public void payByWire(){
        payByWire.click();
    }

    public void confirmOrder(){
        iConfirmOrder.click();
    }
    public void clickBackToOrders(){
        backToOrders.click();
    }
    public String getTotalPriceOrdered(){
        return totalPriceOrdered.getText();
    }
    public void clickProceedToShipping(){
        proceedToShipping.click();
    }
    public void clickProceedToPayment(){
        proceedToPayment.click();
    }
}
