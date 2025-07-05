package Tests;

import Base.BaseTest;
import Pages.HomePage;
import Pages.InventoryPage;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class ResetAppTest extends BaseTest {

    WebDriver driver;
    WebDriverWait wait;

    @BeforeMethod
    public void pageSetUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        homePage = new HomePage(driver);
        inventoryPage = new InventoryPage(driver);
    }
    @Test
    public void resetAppIsWorking() throws InterruptedException {
        driver.navigate().to("https://www.saucedemo.com/");
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

        homePage.enterUsername("standard_user");
        homePage.enterPassword("secret_sauce");
        homePage.clickLoginButton();
        try {
            Alert alert = driver.switchTo().alert();
            alert.accept();
        } catch (NoAlertPresentException e) {
        }
        wait.until(ExpectedConditions.urlContains("inventory.html"));
        WebElement newProduct = driver.findElement(By.id("add-to-cart-sauce-labs-backpack"));
        newProduct.click();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        String korpaPosleDodavanja = driver.findElement(By.linkText("1")).getText();
        Assert.assertTrue(korpaPosleDodavanja.equals("1"));

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("react-burger-menu-btn")));
        inventoryPage.clickOnMeni();
        Thread.sleep(2000);
        WebElement reset = driver.findElement(By.id("reset_sidebar_link"));
        reset.click();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        boolean cartEmpty = driver.findElements(By.className("shopping_cart_badge")).isEmpty();
        Assert.assertTrue(cartEmpty, "Cart should be empty after app reset.");
    }
    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}