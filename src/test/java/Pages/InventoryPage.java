package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class InventoryPage {
    WebDriver driver;
    WebElement addToCart; // className btn btn_primary btn_small btn_inventory
    WebElement slika;
    WebElement korpa;
WebElement meni;
    public InventoryPage(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement getAddToCart() {
        return driver.findElement(By.cssSelector(".btn.btn_primary.btn_small.btn_inventory"));
    }

    public WebElement getMeni() {
        return driver.findElement(By.id("react-burger-menu-btn"));
    }

    public WebElement getSlika() {
        return driver.findElement(By.cssSelector(".inventory_item_img img"));
    }

    public void clickOnSlika() {
        slika.click();
    }

    public WebElement getKorpa() {
        return driver.findElement(By.cssSelector(".shopping_cart_badge"));
    }


    public void clickOnAddToCart() {
        getAddToCart().click();
    }
    public void clickOnMeni() {
        getMeni().click();
    }
}
