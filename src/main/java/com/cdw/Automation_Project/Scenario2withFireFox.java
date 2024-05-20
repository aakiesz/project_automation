package com.cdw.Automation_Project;


 
import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;

import org.testng.annotations.AfterTest;

import org.testng.annotations.BeforeTest;

import org.testng.annotations.Test;
 
public class Scenario2withFireFox {

    protected WebDriver driver;
 
    @BeforeTest

    public void chrome() {

        driver = new FirefoxDriver();

    }
 
    @Test

    public void testBasicAuth() {
 
    	driver.get("http://admin:admin@the-internet.herokuapp.com/basic_auth");

    	String ActualResult = driver.findElement(By.xpath("//p[normalize-space(text())='Congratulations! You must have the proper credentials.']")).getText();
System.out.println(ActualResult);
    	String ExpectedResult = "Congratulations! You must have the proper credentials.";

    	Assert.assertEquals(ActualResult, ExpectedResult);

    }
 
  @AfterTest
 public void tearDown() { 
     driver.quit();

 }

}



