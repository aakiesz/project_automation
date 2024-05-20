package com.cdw.Automation_Project;


 
import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import org.testng.annotations.AfterTest;

import org.testng.annotations.BeforeTest;

import org.testng.annotations.Test;
 
public class Scenario2WithChrome {

    protected WebDriver chromeDriver;
 
    @BeforeTest

    public void chrome() {

        chromeDriver = new ChromeDriver();

    }
 
    @Test

    public void testBasicAuth() {
 
    	chromeDriver.get("http://admin:admin@the-internet.herokuapp.com/basic_auth");

    	String ActualResult = chromeDriver.findElement(By.xpath("//p[normalize-space(text())='Congratulations! You must have the proper credentials.']")).getText();
System.out.println(ActualResult);
    	String ExpectedResult = "Congratulations! You must have the proper credentials.";

    	Assert.assertEquals(ActualResult, ExpectedResult);

    }
 
  @AfterTest
 public void tearDown() { 
     chromeDriver.quit();

 }

}



