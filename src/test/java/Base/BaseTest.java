package Base;

import Pages.HomePage;
import Pages.InventoryPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

public class BaseTest {

    public WebDriver driver;
    public WebDriverWait wait;
    public HomePage homePage;
    public InventoryPage inventoryPage;

    @BeforeClass
    public void setUp() {
        WebDriverManager.chromedriver().setup();
    }


}
