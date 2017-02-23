import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.WebSushiMainPage;

import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertNotEquals;

public class CancelOrderTest {

    private WebDriver driver;

    private static final int NUMBER_OF_ELEMENTS = 5;

    @BeforeMethod
    private void setUp() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @AfterMethod
    private void tearDown() {
        driver.quit();
    }

    @Test
    public void cancelOrderTest() {
        WebSushiMainPage mainPage = new WebSushiMainPage(driver);
        mainPage.openPage();

        int totalPrice = mainPage.addElementsToCart(NUMBER_OF_ELEMENTS);
        int cartProductsNumberBeforeCancelOrder = mainPage.getActualCartProductsNumber();
        CartPage cart = mainPage.checkout(totalPrice, NUMBER_OF_ELEMENTS);
        cart.cancelOrder();
        int cartProductsNumberAfterCancelOrder = mainPage.getActualCartProductsNumber();

        assertNotEquals(cartProductsNumberBeforeCancelOrder, cartProductsNumberAfterCancelOrder);
    }

}