package com.guru.qa.pages;
import com.guru.qa.base.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Guru99LoginPage extends TestBase {

    //private WebDriver driver;

    // 1. By Locators: OR

    @FindBy(name  = "uid")
    WebElement username ;

    @FindBy(name  = "password")
    WebElement pwd ;

    @FindBy(name  = "btnLogin")
    WebElement SubmitBtn ;



    // 2. Constructor of the page class:
    public Guru99LoginPage() {
        PageFactory.initElements(driver,this);

    }

    //3 Operation on Webelement

    public void setusername(String user)
    {
        username.sendKeys(user);
    }

    public void setpasword(String password)
    {
        pwd.sendKeys(password);
    }

    public void Loginbtnclick(){
        SubmitBtn.click();
    }

    public void ClearUsername()
    {
        username.clear();
    }

}

