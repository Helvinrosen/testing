package com.example;


import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.github.bonigarcia.wdm.WebDriverManager;

// import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    // ExtentReports extent;
    // ExtentTest test;
    WebDriver driver;
    String url="";
    @org.testng.annotations.BeforeTest
    public void BeforeTest() throws InterruptedException, IOException
    {
        // ExtentSparkReporter spark=new ExtentSparkReporter("D:\\Software Testing\\Model\\abhibus\\example.html");
        // extent=new ExtentReports();
        // extent.attachReporter(spark);
        FileInputStream fs = new FileInputStream("C:\\Users\\helvi\\OneDrive\\Desktop\\MODEL\\model\\EXCEL.xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook(fs);
        XSSFSheet sheet = workbook.getSheetAt(0);
        XSSFRow row = sheet.getRow(0);
        url = row.getCell(0).getStringCellValue();
        WebDriverManager.chromedriver().setup();
        driver=new ChromeDriver();
        driver.get("https://www.abhibus.com/?utm_source=google&utm_medium=cpc&utm_campaign=Abhibus_Brand&utm_term=abhibus&utm_content=Brand&gad_source=1&gclid=CjwKCAjw88yxBhBWEiwA7cm6pWilIqo8aGBCOEbzcm8_m-nGhw3rNSmK-EJwz3J65D7n8TAsGifFkxoCr-sQAvD_BwE");
        driver.manage().window().maximize();
    
        
    }
    // @BeforeMethod
    // public void BeforeMethod() throws InterruptedException
    // {

    // }    
    
    @Test
    public void Test1() throws Exception
    {  
        // test=extent.createTest("Test1","Test Description1");
        WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));

        driver.findElement(By.xpath("/html/body/header/div/div[2]/div/nav/a[2]")).click();
        Boolean p=driver.getCurrentUrl().contains("trains");
            if(p)
            Assert.assertTrue(true);
            else{
                Assert.assertFalse(false);
            }
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div[1]/div/div[1]/a")));
        driver.findElement(By.xpath("/html/body/div/div[1]/div/div[1]/a")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"login-link\"]")));
        driver.findElement(By.xpath("//*[@id=\"login-link\"]")).click();
        Assert.assertEquals(driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/div/div/div/div[1]/div/div/div[1]/h4")).getText(), "Login to AbhiBus");

       
        File ss=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        String path="C:\\Users\\helvi\\OneDrive\\Desktop\\MODEL\\model\\ss.png";
        FileUtils.copyFile(ss,new File(path));
        
        
        
    }
    @Test
    public void Test2() throws Exception
    {  
        // test=extent.createTest("Test2","Test Description2");
        
        driver.findElement(By.xpath("//*[@id=\"search-from\"]/div/div/div/div/div[2]/input")).click();
        
        Thread.sleep(3000);
        WebElement element = driver.findElement(By.xpath("//*[@id=\"search-from\"]/div/div/div/div/div[2]/input"));
        element.sendKeys("Mumbai");
        Thread.sleep(3000);
        element.sendKeys(Keys.ARROW_DOWN);
        element.sendKeys(Keys.ENTER);
         WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));
         wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"search-to\"]/div/div/div/div/div[2]/input")));
        
         element = driver.findElement(By.xpath("//*[@id=\"search-to\"]/div/div/div/div/div[2]/input"));
         element.sendKeys("Pune");
        Thread.sleep(3000);
        element.sendKeys(Keys.ARROW_DOWN);
        element.sendKeys(Keys.ENTER);
         Thread.sleep(3000);
        
         driver.findElement(By.xpath("//*[@id=\"search-button\"]/button")).click();
         assert driver.getCurrentUrl().contains("Mumbai") && driver.getCurrentUrl().contains("Pune");
         
    }
    @Test
    public void Test3() throws Exception
     {  
        // test=extent.createTest("Test3","Test Description 3");
        JavascriptExecutor js=(JavascriptExecutor)driver;
        js.executeScript("window.scrollBy(0,5500)");
        Thread.sleep(3000);
        driver.findElement(By.xpath("//*[@id=\"footer-routes\"]/div/div[1]/div/div/div/div/button[5]")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id='footer-routes']/div/div[2]/div/div/div/div[7]/a")).click();
        String name=driver.findElement(By.xpath("/html/body/div[4]/div[1]/div[1]/h2")).getText();
       
        Assert.assertEquals(name,"Media");
        
        File ss=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        String path="C:\\Users\\helvi\\OneDrive\\Desktop\\MODEL\\model\\Media.png";
        FileUtils.copyFile(ss,new File(path));
        
    }
    @AfterTest
    public void AfterMethod(ITestResult result) throws IOException
    {
        // if(result.getStatus()==ITestResult.FAILURE)
        // {
        //   test.log(Status.FAIL,"Testcase"+result.getName());
        //   test.log(Status.FAIL,"Testcase reason"+result.getThrowable());
        //   File ss=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        //   String path="C:\\Users\\helvi\\OneDrive\\Desktop\\MODEL\\model\\"+result.getName()+".png";
        //   FileUtils.copyFile(ss,new File(path));
        //   test.addScreenCaptureFromPath(path);
        
        // }
        // else if(result.getStatus()==ITestResult.SUCCESS)
        // {
        //     test.log(Status.PASS,"Testcase"+result.getName());
        // }
        // else if(result.getStatus()==ITestResult.SKIP)
        // {
        //     test.log(Status.SKIP,"Testcase"+result.getName());
        // }
        // extent.flush();
        driver.close();
    }

}

