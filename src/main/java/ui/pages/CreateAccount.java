package ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static ui.BaseTest.waitForSomeTime;

public class CreateAccount {

    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(id = "id_gender1")
    private WebElement titleMr;
    @FindBy(id = "uniform-id_gender2")
    private WebElement titleMrs;

    @FindBy(id = "customer_firstname")
    private WebElement firstName;
    @FindBy(id = "customer_lastname")
    private WebElement lastName;
    @FindBy(id = "passwd")
    private WebElement password;

    @FindBy(id = "days")
    private WebElement dayDOB;
    @FindBy(id = "months")
    private WebElement monthDOB;
    @FindBy(id = "years")
    private WebElement yearsDOB;

    @FindBy(id = "uniform-newsletter")
    private WebElement checkNewsLetter;
    @FindBy(id = "uniform-optin")
    private WebElement checkOptIn;

    //Address fields
    @FindBy(id = "firstname")
    private WebElement addrFirstName;
    @FindBy(id = "lastname")
    private WebElement addrLastName;
    @FindBy(id = "company")
    private WebElement companyName;
    @FindBy(id = "address1")
    private WebElement addressLine1;
    @FindBy(id = "address2")
    private WebElement addressLine2;
    @FindBy(id = "city")
    private WebElement addressCity;
    @FindBy(id = "id_state")
    private WebElement addressState;
    @FindBy(id = "postcode")
    private WebElement addressZipCode;
    @FindBy(id = "id_country")
    private WebElement addressCountry;
    @FindBy(id = "other")
    private WebElement additionalInfo;
    @FindBy(id = "phone_mobile")
    private WebElement mobilePhone;
    @FindBy(id = "alias")
    private WebElement addressAlias;
    @FindBy(id = "submitAccount")
    private WebElement registerAccount;

    private String title, firstNameVal, lastNameVal, passwordVal, firstNameAddr, lastNameAddr, month, company, addrLine,
            cityVal, stateVal, countryVal, addtionalInformation, mobilePhoneNumb, addrAlias;
    private int day, year, addrZipCode;
    private boolean subscribeForNewsLetter, optInForSpecialOffers;

    public CreateAccount(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 10);
        PageFactory.initElements(driver, this);
    }

    public void setTitle(String title) {
        waitForSomeTime(3);
        this.title = title;
        if(title == null || title.trim().toLowerCase().equals("mr")){
            titleMr.click();
        }
        else {
            titleMrs.click();
        }
    }

    public void setFirstNameVal(String firstNameVal) {
        this.firstNameVal = firstNameVal;
        if(firstNameVal != null){
            firstName.sendKeys(firstNameVal);
        }
    }

    public void setLastNameVal(String lastNameVal) {
        if(lastNameVal != null){
            lastName.sendKeys(lastNameVal);
        }
        this.lastNameVal = lastNameVal;
    }

    public void setPasswordVal(String passwordVal) {
        this.passwordVal = passwordVal;
        password.sendKeys(passwordVal);
    }

    public void setMonth(String month) {
        this.month = month;
        selectListElement(monthDOB, month);
    }

    public void setFirstNameAddr(String firstNameAddr) {
        this.firstNameAddr = firstNameAddr;
        addrFirstName.sendKeys(firstNameAddr);
    }

    public void setLastNameAddr(String lastNameAddr) {
        this.lastNameAddr = lastNameAddr;
        addrLastName.sendKeys(lastNameAddr);
    }

    public void setCompany(String company) {
        this.company = company;
        companyName.sendKeys(company);
    }

    public void setAddrLine(String addrLine) {
        this.addrLine = addrLine;
        addressLine1.sendKeys(addrLine);
    }

    public void setCityVal(String cityVal) {
        this.cityVal = cityVal;
        addressCity.sendKeys(cityVal);
    }

    public void setStateVal(String stateVal) {
        this.stateVal = stateVal;
        selectListElement(addressState, stateVal);
    }

    public void setCountryVal(String countryVal) {
        this.countryVal = countryVal;
        selectListElement(addressCountry, countryVal);
    }

    public void setAddtionalInformation(String addtionalInformation) {
        this.addtionalInformation = addtionalInformation;
        additionalInfo.sendKeys(addtionalInformation);
    }

    public void setMobilePhoneNumb(String mobilePhoneNumb) {
        this.mobilePhoneNumb = mobilePhoneNumb;
        mobilePhone.sendKeys(mobilePhoneNumb);
    }

    public void setAddrAlias(String addrAlias) {
        this.addrAlias = addrAlias;
        addressAlias.sendKeys(addrAlias);
    }

    public void setDay(int day) {
        this.day = day;
        selectListElement(dayDOB, day);
    }

    public void setYear(int year) {
        this.year = year;
        selectListElement(yearsDOB, year);
    }

    public void setAddrZipCode(int addrZipCode) {
        this.addrZipCode = addrZipCode;
        addressZipCode.sendKeys(String.valueOf(addrZipCode));
    }

    public void setSubscribeForNewsLetter(boolean subscribeForNewsLetter) {
        this.subscribeForNewsLetter = subscribeForNewsLetter;
        if(subscribeForNewsLetter){
            checkNewsLetter.click();
        }
    }

    public void setOptInForSpecialOffers(boolean optInForSpecialOffers) {
        this.optInForSpecialOffers = optInForSpecialOffers;
        if(optInForSpecialOffers){
            checkOptIn.click();
        }
    }

    public void registerAccount(){
        registerAccount.click();
    }

    public void selectListElement(WebElement parentElement, Object val){
        parentElement.click();
        List<WebElement> elements = parentElement.findElements(By.tagName("option"));
        System.out.println("value to select : " + val);
        for(WebElement element: elements){
            System.out.println("List element : " + element.getText());
            if(element.getText().trim().equals(String.valueOf(val))){
                element.click();
            }
        }

    }
}
