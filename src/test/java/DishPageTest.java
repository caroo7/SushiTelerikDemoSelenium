import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.DishPage;
import pages.WebSushiMainPage;

import java.util.concurrent.TimeUnit;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.number.OrderingComparison.greaterThan;
import static org.hamcrest.number.OrderingComparison.lessThan;
import static org.testng.Assert.assertEquals;

public class DishPageTest {

    private WebDriver driver;

    private static final int DISH_NUMBER = 10;

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
    public void addToCartFromDishViewTest() {
        WebSushiMainPage mainPage = new WebSushiMainPage(driver);
        mainPage.openPage();

        DishPage dishPage = mainPage.openDishPage(DISH_NUMBER);
        dishPage.addDishToTheCart();

        assertEquals(dishPage.getActualCartProductsNumber(), 1);
    }

    @Test
    public void navigateButtonsTest() {
        WebSushiMainPage mainPage = new WebSushiMainPage(driver);
        mainPage.openPage();

        DishPage dishPage = mainPage.openDishPage(DISH_NUMBER);
        int previousElementNumber = dishPage.previousElementNumber();
        int nextElementNumber = dishPage.nextElementNumber();

        assertThat(previousElementNumber, lessThan(DISH_NUMBER));
        assertThat(nextElementNumber, greaterThan(DISH_NUMBER));
    }

}