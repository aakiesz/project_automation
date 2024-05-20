package com.cdw.Automation_Project;

import static org.testng.Assert.assertEquals;
import java.time.Duration;
import java.util.List;

import org.bouncycastle.oer.its.ieee1609dot2.basetypes.PublicEncryptionKey;
import org.openqa.selenium.By;
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
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class scenario1withfirefox {

    @Test
    public void test() {
        WebDriver driver = new FirefoxDriver();
        driver.get("https://magento.softwaretestingboard.com/");
        driver.manage().window().maximize();
        Reporter.log("Browser launched and navigated to Magento website.", true);

        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofSeconds(5))
                .ignoring(StaleElementReferenceException.class);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

        // Create a customer with valid info and sign out
        driver.findElement(By.linkText("Create an Account")).click();
        driver.findElement(By.id("firstname")).sendKeys("aakiesz");
        driver.findElement(By.id("lastname")).sendKeys("shaik");
        driver.findElement(By.id("email_address")).sendKeys("shau98@gmail.com");
        driver.findElement(By.id("password")).sendKeys("password@123");
        driver.findElement(By.id("password-confirmation")).sendKeys("password@123");
        driver.findElement(By.cssSelector("button[title='Register']")).click(); 
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@type='button']"))); 
        driver.findElement(By.linkText("Sign Out")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Sign In"))); 


        // Login with created valid credentials
        Reporter.log("Attempting to login with valid credentials.", true);
        driver.findElement(By.linkText("Sign In")).click();
        driver.findElement(By.id("email")).sendKeys("shau98@gmail.com");
        driver.findElement(By.id("pass")).sendKeys("password@123");
        driver.findElement(By.xpath("//button[@id='send2']/span")).click();
        Reporter.log("Logged in successfully.", true);

        // Add product via category drop-down
        Reporter.log("Adding a product via category drop-down.", true);
        WebElement hoverElement = driver.findElement(By.xpath("//span[text()='Men']"));
        Actions actions = new Actions(driver);
        actions.moveToElement(hoverElement).build().perform();
        WebElement hoverElement1 = driver.findElement(By.xpath("//a[@id='ui-id-17']/span[2]"));
        actions.moveToElement(hoverElement1).build().perform();
        WebElement hoverElement2 = driver.findElement(By.xpath("//a[@id='ui-id-19']/span"));
        hoverElement2.click();
        driver.findElement(By.xpath("//img[@alt='Proteus Fitness Jackshirt']")).click();
        driver.findElement(By.id("option-label-size-143-item-169")).click();
        driver.findElement(By.id("option-label-color-93-item-50")).click();
        driver.findElement(By.id("qty")).clear();
        driver.findElement(By.id("qty")).sendKeys("2");
        driver.findElement(By.xpath("//button[@id='product-addtocart-button']/span")).click();
        Reporter.log("Product added to cart via category drop-down.", true);

        // Add product via search
        Reporter.log("Adding a product via search.", true);
        driver.findElement(By.id("search")).click();
        driver.findElement(By.id("search")).sendKeys("pants");
        driver.findElement(By.id("search_mini_form")).submit();
        driver.findElement(By.xpath("//img[@alt='Caesar Warm-Up Pant']")).click();
        driver.findElement(By.id("option-label-size-143-item-177")).click();
        driver.findElement(By.id("option-label-color-93-item-52")).click();
        driver.findElement(By.id("qty")).clear();
        driver.findElement(By.id("qty")).sendKeys("2");
        driver.findElement(By.id("product-addtocart-button")).click();
        Reporter.log("Product added to cart via search.", true);

        // Print the number of Products listed on the page, Fetch the Product name and price of the products on the page.
        Reporter.log("Fetching product names and prices listed on the page.", true);
        driver.findElement(By.id("search")).clear();
        driver.findElement(By.id("search")).sendKeys("pants");
        driver.findElement(By.id("search_mini_form")).submit();
        System.out.println("**** displaying the names and prices of the products ****");

        try {
            List<WebElement> name = driver.findElements(By.xpath("//a[@class='product-item-link']"));
            List<WebElement> prices = driver.findElements(By.xpath("//span[@class='price']"));

            for (int i = 0; i < name.size(); i++) {
                WebElement priceElement = prices.get(i);
                WebElement productElement = name.get(i);

                String cost = priceElement.getText();
                String productName = productElement.getText();
                System.out.println("Name of product: " + productName);
                System.out.println("Price: " + cost);
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Index out of bounds: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
        }
        
        Reporter.log("Product names and prices fetched successfully.", true);
        System.out.println("=====================================================================");

        // Sorting with price
        Reporter.log("displaying products by price high to low.", true);
        
        
        driver.findElement(By.id("sorter")).click();
        new Select(driver.findElement(By.id("sorter"))).selectByVisibleText("Price");
        
        
        try {
        	  List<WebElement> name = driver.findElements(By.xpath("//a[@class='product-item-link']"));
  		        List<WebElement> prices = driver.findElements(By.xpath("//span[@class='price']"));
  		        
                for (int i = 0; i < name.size(); i++) {  
                WebElement priceElement = prices.get(i);
                WebElement productElement = name.get(i);

                String cost = priceElement.getText();
                String productName = productElement.getText();
                System.out.println("Name of product: "+productName );
                System.out.println("Price: " + cost);            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Index out of bounds: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
        }
        
      //Assert if the sort is working as expected
        
        String ActualhighestPrice = driver.findElement(By.xpath("(//span[@class='price'])[5]")).getText();
        String ExpectedHighestPrice = "$75.00";
        assertEquals(ActualhighestPrice, ExpectedHighestPrice);
        Reporter.log("Sort is working as expected 😇.", true);

        driver.findElement(By.id("sorter")).click();
        new Select(driver.findElement(By.id("sorter"))).selectByVisibleText("Relevance");

        // Add another product to the cart
        Reporter.log("Adding another product to the cart.", true);
        driver.findElement(By.id("search")).clear();
        driver.findElement(By.id("search")).sendKeys("pants");
        driver.findElement(By.id("search_mini_form")).submit();
        JavascriptExecutor js = (JavascriptExecutor) driver;

        WebElement hoverElement3 = driver.findElement(By.xpath("(//img[@class='product-image-photo'])[4]"));
        Actions action3 = new Actions(driver);
        action3.moveToElement(hoverElement3).perform();
        driver.findElement(By.xpath("(//div[@aria-label='33'])[3]")).click();
        driver.findElement(By.xpath("(//div[@option-label='Black'])[2]")).click();
        driver.findElement(By.xpath("(//span[text()='Add to Cart'])[3]")).click();
        Reporter.log("Product added to cart successfully.", true);

        // Add to wishlist
        Reporter.log("Adding product to wishlist.", true);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//a[@aria-label='Add to Wish List'])[3]"))).click();
        Reporter.log("Product added to wishlist successfully.", true);

        // Verify product name and description
        Reporter.log("Verifying product name and description.", true);
        WebElement confirmName = driver.findElement(By.xpath("(//div[@class='message-success success message'])[1]"));
        String actualName = confirmName.getText();
        String expectedName = "You added Livingston All-Purpose Tight to your shopping cart.";
        System.out.println("Product name and description: " + actualName);
        Assert.assertEquals(actualName, expectedName);
        Reporter.log("Product name and description verified successfully.", true);

        // Verify size
        Reporter.log("Verifying product size.", true);
        driver.findElement(By.xpath("//a[@class='action showcart']")).click();
        driver.findElement(By.xpath("(//span[@class='toggle'])[1]")).click();
        WebElement confirmSize = driver.findElement(By.xpath("(//span[@data-bind='text: option.value'])[1]"));
        String actualSize = confirmSize.getText();
        String expectedSize = "33";
        System.out.println("Product size: " + actualSize);
        Assert.assertEquals(actualSize, expectedSize);
        Reporter.log("Product size verified successfully.", true);

        // Proceed to checkout and verify the bill amount
        Reporter.log("Proceeding to checkout and verifying the bill amount.", true);
        driver.findElement(By.id("top-cart-btn-checkout")).click();
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("shipping")));
        driver.findElement(By.xpath("(//input[@class='input-text'])[6]")).sendKeys("fhgf");
        driver.findElement(By.xpath("(//input[@class='input-text'])[9]")).sendKeys("chennai");
        new Select(driver.findElement(By.xpath("(//select[@class='select'])[1]"))).selectByVisibleText("Alabama");
        driver.findElement(By.xpath("(//input[@class='input-text'])[11]")).sendKeys("76567");
        driver.findElement(By.xpath("(//input[@class='input-text'])[12]")).sendKeys("76567867");

        driver.findElement(By.xpath("(//td[@class='col col-price'])[1]")).click();

        WebElement codeElement2 = driver.findElement(By.xpath("//button[@class='button action continue primary']"));
        js.executeScript("arguments[0].scrollIntoView(true);", codeElement2);
        codeElement2.click();
        WebElement element2 = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='checkout-payment-method-load']/div/div/div")));
        String ActualTotalBillAmount = driver.findElement(By.xpath("//td[@data-th='Order Total']")).getText();

        String ExpectedTotalBillAmount = "$188.00";
        System.out.println("Total bill amount: " + ActualTotalBillAmount);
        assertEquals(ActualTotalBillAmount, ExpectedTotalBillAmount);
        Reporter.log("Bill amount verified successfully.", true);

        driver.quit();
    }
}
