package ui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import static ui.BaseTest.waitForSomeTime;

public class Authenticate {

    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(id = "SubmitCreate") private WebElement createAccountButton;
    @FindBy(id = "email_create") private WebElement emailCreateAccount;
    @FindBy(id = "email") private WebElement signInEmail;
    @FindBy(id = "passwd") private WebElement signInPassword;
    @FindBy(id = "SubmitLogin") private WebElement signInButton;

    String newEmailAddr, emailAddr, password;

    public Authenticate(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 10);
        PageFactory.initElements(driver, this);
    }

    public void clickCreateAccountButton() {
        createAccountButton.click();
        waitForSomeTime(3);
        createAccountButton.click();
        waitForSomeTime(3);
    }

    public void setNewEmailAddr(String newEmailAddr) {
        this.newEmailAddr = newEmailAddr;
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        emailCreateAccount.sendKeys(newEmailAddr);
    }

    public void setEmailAddr(String emailAddr) {
        this.emailAddr = emailAddr;
        signInEmail.sendKeys(emailAddr);
    }

    public void setPassword(String password) {
        this.password = password;
        signInPassword.sendKeys(password);
    }

    public void clickSignInButton() {
        signInButton.click();
    }

    public void signIn() {
        AutomationHome homePage = new AutomationHome(driver);
        Authenticate authenticate = new Authenticate(driver);
        homePage.signIn();
        authenticate.setEmailAddr("test-amit@test.com");
        authenticate.setPassword("12345678");
        clickSignInButton();
    }
}
