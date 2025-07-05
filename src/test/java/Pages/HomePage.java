package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage {

    WebDriver driver;
    WebElement inputUsername;
    WebElement inputPassword;
    WebElement loginButton;

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement getInputUsername() {
        return driver.findElement(By.id("user-name"));
    }

    public WebElement getInputPassword() {
        return driver.findElement(By.id("password"));
    }

    public WebElement getLoginButton() {
        return driver.findElement(By.id("login-button"));
    }

    public void enterUsername(String username) {
        getInputUsername().clear();
        getInputUsername().sendKeys(username);
    }
    public void enterPassword(String password) {
        getInputPassword().clear();
        getInputPassword().sendKeys(password);
    }
    public void clickLoginButton() {
        getLoginButton().click();
    }
}
