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

public class AddToCardTest extends BaseTest {
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
    public void addToCardWorks() throws InterruptedException {
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

        boolean prviKlik = false;
        boolean drugiKlik = false;
        try {
            wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".btn.btn_primary.btn_small.btn_inventory")));
            prviKlik = true;
            String nazivPreKlika = driver.findElement(By.linkText("Add to card")).getText();
            Assert.assertTrue(nazivPreKlika.equals("Add to card"));
        } catch (Exception e) {
            prviKlik = false;
        }

        try {
            wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".btn.btn_primary.btn_small.btn_inventory")));
            drugiKlik = true;
            inventoryPage.getAddToCart().click();
            String nazivPosleKlika = driver.findElement(By.linkText("Remove")).getText();
            Assert.assertTrue(nazivPosleKlika.equals("Remove"));
        } catch (Exception e) {
            drugiKlik = false;
        }
    }

    @Test
    public void cannotAddMoreThanOneItem() throws InterruptedException {
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
        boolean preKlika = false;
        boolean posleKlika = false;

        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("add-to-cart-sauce-labs-backpack")));
            preKlika = true;
            inventoryPage.clickOnAddToCart();
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("remove-sauce-labs-backpack")));
            String expectedLabel = "Remove";
            String actualLabel = inventoryPage.getAddToCart().getText();
            Assert.assertNotEquals(actualLabel, expectedLabel, "Add to card dugme je vidljivo, moze se dodati jos proizvoda");
        } catch (Exception e) {
            preKlika = false;
        }
        String korpaPosleDodavanja = driver.findElement(By.linkText("1")).getText();
        Assert.assertTrue(korpaPosleDodavanja.equals("1"));
        }
    }

