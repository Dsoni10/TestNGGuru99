package com.guru.qa.testcases;

import com.guru.qa.base.TestBase;
import com.guru.qa.pages.Guru99LoginPage;
import com.guru.qa.utill.TestUtill;
import org.apache.commons.io.FileUtils;
import org.apache.poi.util.SystemOutLogger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GuruLoginTestCases extends TestBase {

    Guru99LoginPage loginPage;
    TestUtill utility1 ;

    @BeforeMethod(enabled = true)
    public void setup() throws InterruptedException {
        initialization();
        Thread.sleep(3000);
        loginPage = new Guru99LoginPage();
        utility1 = new TestUtill();

    }
    
    
    
    
    @DataProvider(name = "Aunthetication")
    public Object[][] ABC()
    {
        return new Object[][] {{"mngr385129","netYbYp"},{"invalid","valid"},{"valid","invalid"},{"invalid","invalid"}};

    }

    @Test(enabled = true)
    public void LoginPageTest() throws InterruptedException {


        loginPage.setusername(prop.getProperty("username"));
        loginPage.setpasword(prop.getProperty("password"));
        loginPage.Loginbtnclick();
        Thread.sleep(3000);


    }

    @Test(dataProvider = "Aunthetication",enabled = false)
    public void LoginPageTestWithDataProvider(String usernameValue,String passwordValue) throws InterruptedException {
        String actualBoxTitle;
        loginPage.ClearUsername();
        loginPage.setusername(usernameValue);
        loginPage.setpasword(passwordValue);
        loginPage.Loginbtnclick();

        Thread.sleep(3000);

        try{
            Alert alert = driver.switchTo().alert();
            actualBoxTitle= alert.getText();
            alert.accept();

            if(actualBoxTitle.equalsIgnoreCase(actualBoxTitle))
            {
                System.out.println("Test case SS Passed");
            }
            else{
                System.out.println("Test case SS Failed");
            }




        }
        catch(NoAlertPresentException EX)
        {
            String actual_result = driver.getTitle();
            Assert.assertEquals(actual_result,"Guru99 Bank Manager HomePage","Sucessfully Logged in on Page");


        }


    }


    @Test(enabled = false)
    public void LoginWithDifferentUser(String usernameValue,String passwordValue)
    {
        String[][] credential = TestUtill.getTestData("Data");
        System.out.println(credential[1][1]);

        loginPage.setusername(credential[1][1]);
        loginPage.setpasword(credential[1][2]);
        loginPage.Loginbtnclick();

    }


    @Test(enabled = false)
    public void testdatacheckup()
    {
        String[][] credential = TestUtill.getTestData("Data");
        System.out.println("length"+credential.length);

        for (int i=1;i<= credential.length;i++)
        {
            System.out.println("InsideLoop");
            System.out.println(credential[i][2]);

        }
        //System.out.println(credential[1][1]);

    }

    @Test(dataProvider = "Aunthetication" ,enabled = false)
    public void TakeScreenshotPages(String usernameValue,String passwordValue) throws IOException {

        String actualBoxTitle;
        loginPage.ClearUsername();
        loginPage.setusername(usernameValue);
        loginPage.setpasword(passwordValue);
        loginPage.Loginbtnclick();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try{
            Alert alert = driver.switchTo().alert();
            actualBoxTitle= alert.getText();
            alert.accept();

            if(actualBoxTitle.equalsIgnoreCase(actualBoxTitle))
            {
                System.out.println("Test case SS Passed");
            }
            else{
                System.out.println("Test case SS Failed");
            }




        }
        catch(NoAlertPresentException EX)
        {
            String actual_result = driver.getTitle();
            Assert.assertEquals(actual_result,"Guru99 Bank Manager HomePage","Sucessfully Logged in on Page");


        }

        String timestemp = new SimpleDateFormat("YYMMDD_HHMMSS").format(new Date());
        String filename = "IMG"+ timestemp + ".jpg";

        File Src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(Src,new File("E:/deshmukhTestNGGuru99/screenshots/"+filename));


    }



    @AfterMethod(enabled = true)
    public void tearDown(){
        driver.quit();
    }


}
