package com.cdw.Automation_Project;

import static org.testng.Assert.assertEquals;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.By.ByName;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class Scenario3withFIreFox{
    @Test
    public void test() throws InterruptedException {
        // navigate to the URL
        WebDriver driver = new FirefoxDriver();
        driver.get("https://magento.softwaretestingboard.com/");
        driver.manage().window().maximize();

        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofSeconds(5))
                .ignoring(StaleElementReferenceException.class);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

        // land on the PLP page
        driver.findElement(By.id("search")).click();
        driver.findElement(By.id("search")).sendKeys("pants");
        driver.findElement(By.id("search_mini_form")).submit();
        JavascriptExecutor js = (JavascriptExecutor) driver;

        // Login with the same credentials created in Scenario 1 assert if the user is landed to My wish list page.
        WebElement hoverElement3 = driver.findElement(By.xpath("(//img[@class='product-image-photo'])[3]"));
        Actions action3 = new Actions(driver);
        action3.moveToElement(hoverElement3).perform();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//a[@aria-label='Add to Wish List'])[3]"))).click();
        String actualPage = driver.findElement(By.xpath("//span[@class='base']")).getText();
        String expectedPage = "Customer Login";
        assertEquals(actualPage, expectedPage);
        Reporter.log("Asserted user is on Customer Login page");

        // Login with the same credentials created in Scenario 1 assert if the user is landed to My wish list page.
        driver.findElement(By.name("login[username]")).sendKeys("shaikk@gmail.com");
        driver.findElement(By.name("login[password]")).sendKeys("password@123");
        driver.findElement(By.xpath("(//span[text()='Sign In'])[1]")).click();
        Thread.sleep(3000);

        // Assert the product name & price are same as it was added by user.
        driver.findElement(By.xpath("//a[@class='action showcart']")).click();
        driver.findElement(By.xpath("(//span[@class='toggle'])[1]")).click();
        WebElement confirmSize = driver.findElement(By.xpath("(//span[@data-bind='text: option.value'])[1]"));
        String actualSize = confirmSize.getText();
        String expectedSize = "33";
        System.out.println("Product size: " + actualSize);
        Assert.assertEquals(actualSize, expectedSize);
        Reporter.log("Asserted product size is as expected");

        WebElement confirmName = driver.findElement(By.xpath("(//a[@title='Livingston All-Purpose Tight'])[3]"));
        String actualName = confirmName.getText();
        String expectedName = "Livingston All-Purpose Tight";
        System.out.println("Product name: " + actualName);
        Assert.assertEquals(actualName, expectedName);
        Reporter.log("Asserted product name is as expected");

        driver.quit();
    }
}
