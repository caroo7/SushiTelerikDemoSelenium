package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class WebSushiMainPage {

    private static final String pageURL = "http://demos.telerik.com/kendo-ui/websushi/#/";

    private static final String basedPartPath = ".//*[@id='main']/li";

    private static final String addButtonPartPath = "/button";

    private static final String pricePartPath = "/a/span/span[2]";

    private static final String checkoutId = "checkout";

    private static final String emptyCartId = "empty-cart";

    private static final String cartItemsNumberCss = "#cart-info>span";

    private static final String dishPageBasePartPath = ".//*[@id='main']/li";

    private WebDriver driver;

    public WebSushiMainPage(WebDriver driver) {
        this.driver = driver;
    }

    public void openPage() {
        driver.get(pageURL);
    }

    public int addElementsToCart(int numberOfElements) {
        int totalPriceValue = 0;
        for (int i = 1; i <= numberOfElements; ++i) {
            WebElement addButton = driver.findElement(By.xpath(basedPartPath + "[" + i + "]" + addButtonPartPath));
            addButton.click();

            WebElement priceLabel = driver.findElement(By.xpath(basedPartPath + "[" + i + "]" + pricePartPath));
            totalPriceValue += Integer.parseInt(priceLabel.getText());
        }
        return totalPriceValue;
    }

    public CartPage checkout(int totalPriceValue, int numberOfElements) {
        WebElement checkoutButton = driver.findElement(By.id(checkoutId));
        checkoutButton.click();

        return new CartPage(driver, totalPriceValue, numberOfElements);
    }

    public void removeProductsFromCart() {
        WebElement emptyCartButton = driver.findElement(By.id(emptyCartId));
        emptyCartButton.click();
    }

    public int getActualCartProductsNumber() {
        WebElement cartProductsNumber = driver.findElement(By.cssSelector(cartItemsNumberCss));
        return Integer.parseInt(cartProductsNumber.getText().substring(0, cartProductsNumber.getText().indexOf(' ')));
    }

    public DishPage openDishPage(int number) {
        WebElement dishElement = driver.findElement(By.xpath(dishPageBasePartPath + "[" + number + "]/a"));
        dishElement.click();
        return new DishPage(driver);
    }

}