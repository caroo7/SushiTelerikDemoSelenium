package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class DishPage {

    private WebDriver driver;

    private static final String addToCartButtonPath = ".//*[@id='details-total']/button";

    private static final String itemsInCartNumberPath = ".//*[@id='cart-info']/span";

    private static final String previousButtonPath = ".//*[@id='navigate-prev']";

    private static final String nextButtonPath = ".//*[@id='navigate-next']";

    DishPage(WebDriver driver) {
        this.driver = driver;
    }

    public void addDishToTheCart() {
        WebElement addToCartButton = driver.findElement(By.xpath(addToCartButtonPath));
        addToCartButton.click();
    }

    public int getActualCartProductsNumber() {
        WebElement cartProductsNumber = driver.findElement(By.xpath(itemsInCartNumberPath));
        return Integer.parseInt(cartProductsNumber.getText().substring(0, cartProductsNumber.getText().indexOf(' ')));
    }

    public int previousElementNumber() {
        WebElement previousButton = driver.findElement(By.xpath(previousButtonPath));
        String previousElementLink = previousButton.getAttribute("href");
        int index = previousElementLink.lastIndexOf('/');
        return Integer.parseInt(previousElementLink.substring(index+1, previousElementLink.length()));
    }

    public int nextElementNumber() {
        WebElement nextButton = driver.findElement(By.xpath(nextButtonPath));
        String nextElementLink = nextButton.getAttribute("href");
        int index = nextElementLink.lastIndexOf('/');
        return Integer.parseInt(nextElementLink.substring(index+1, nextElementLink.length()));
    }

}