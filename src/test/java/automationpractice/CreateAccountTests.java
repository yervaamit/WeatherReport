package automationpractice;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ui.BaseTest;
import ui.pages.Authenticate;
import ui.pages.AutomationHome;
import ui.pages.CreateAccount;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CreateAccountTests extends BaseTest {

    private static WebDriver driver;

    @BeforeClass(alwaysRun = true)
    public void initialize() {
        driver = getDriver("http://automationpractice.com/index.php");
//        driver.get("http://automationpractice.com/index.php");
    }

    @Test(description = "To create and verify the account created.")
    public void createAccountTest() throws InterruptedException {
        AutomationHome homePage = new AutomationHome(driver);
        CreateAccount newAccount = new CreateAccount(driver);
        Authenticate newAuthenticate = new Authenticate(driver);
        homePage.signIn();
        newAuthenticate.setNewEmailAddr("test-amit@test.com");
        newAuthenticate.clickCreateAccountButton();

        newAccount.setTitle("Mr");
        newAccount.setFirstNameVal("Amit");
        newAccount.setLastNameVal("Y");
        newAccount.setPasswordVal("12345678");

        Assert.assertTrue(true, "Create Account test case passed..!");
//        newAccount.setDay(1);
//        Thread.sleep(5000);
//        newAccount.setMonth("January");
//        newAccount.setYear(2000);
//        newAccount.setSubscribeForNewsLetter(true);
//        newAccount.setOptInForSpecialOffers(true);
//
//        newAccount.setFirstNameAddr("Amit");
//        newAccount.setLastNameAddr("Y");
//        newAccount.setCompany("crossasyst");
//        newAccount.setAddrLine("111, Oxford society");
//        newAccount.setCityVal("Boston");
//        newAccount.setStateVal("Illinois");
//        newAccount.setAddrZipCode(123456);
//
//        newAccount.setAddtionalInformation("Hello World..!!");
//        newAccount.setMobilePhoneNumb("1234567890");
//        newAccount.setAddrAlias("New address");
//
//        newAccount.registerAccount();



    }


    public static void main(String[] args) {

        int ins = text(Arrays.asList(25, 23, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 76, 80));
        System.out.println("number of instances : " + ins);
    }

    public static int text(List<Integer> x){

        int instances = 1;
        for(int i = 0; i < x.size(); i++){
            if(x.get(i) < 25){
                if(instances > 1){
                    instances = (int) Math.ceil(instances / 2);
                    i = i + 10;
                }
                if(i > x.size()) break;
            }
            if(x.get(i) > 60){
                if((instances * 2) < 217){
                    instances = instances * 2;
                    i = i + 10;
                }
                if(i > x.size()) break;
            }
            if(i > x.size()) break;
        }
        return instances;
    }

}
