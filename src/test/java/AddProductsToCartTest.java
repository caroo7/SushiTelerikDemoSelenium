import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.WebSushiMainPage;

import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertEquals;

public class AddProductsToCartTest {

    private static final int NUMBER_OF_ELEMENTS = 5;

    private WebDriver driver;

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
    public void addSeveralProductsToCart() {
        WebSushiMainPage mainPage = new WebSushiMainPage(driver);
        mainPage.openPage();

        int totalPrice = mainPage.addElementsToCart(NUMBER_OF_ELEMENTS);
        CartPage cart = mainPage.checkout(totalPrice, NUMBER_OF_ELEMENTS);

        assertEquals(cart.getRealElementsNumber(), cart.getExpectedNumberOfElements());
        assertEquals(cart.getRealPriceValue(), cart.getExpectedTotalPriceValue());
    }

    @Test
    public void changeQuantityInCart() {
        WebSushiMainPage mainPage = new WebSushiMainPage(driver);
        mainPage.openPage();

        int totalPrice = mainPage.addElementsToCart(1);
        CartPage cart = mainPage.checkout(totalPrice, 1);
        int expectedValue = cart.changeQuantityOfOrderedItem();

        assertEquals(cart.getRealPriceValue(), expectedValue);
    }

}