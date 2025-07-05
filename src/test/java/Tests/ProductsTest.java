package Tests;

import Base.BaseTest;
import Pages.HomePage;
import Pages.InventoryPage;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class ProductsTest extends BaseTest {
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
    public void differentProducts() throws InterruptedException {
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

        List<WebElement> slike = driver.findElements(By.cssSelector(".inventory_item_img img"));
        boolean slikeIste = true;
        if (slike.size() > 1) {
            String prva = slike.get(0).getAttribute("src");
            for (int i = 0; i < slike.size(); i++) {
                String trenutna = slike.get(i).getAttribute("src");
                if (!prva.equals(trenutna)) {
                    slikeIste = false;
                    break;
                }
            }
            if (slikeIste) {
                throw new AssertionError();
            }
        }

        Assert.assertFalse(slikeIste);
    }
}