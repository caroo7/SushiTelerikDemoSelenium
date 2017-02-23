package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CartPage {

    private static final String allElementsCss = "tbody tr";

    private static final String totalPricePath = "#total-checkout>span";

    private static final String increaseArrowCss = ".k-icon.k-i-arrow-60-up";

    private static final String cancelOrderClass = "cancel-order";

    private WebDriver driver;

    private int expectedTotalPriceValue;

    private int expectedNumberOfElements;

    CartPage(WebDriver driver, int expectedTotalPriceValue, int expectedNumberOfElements) {
        this.driver = driver;
        this.expectedTotalPriceValue = expectedTotalPriceValue;
        this.expectedNumberOfElements = expectedNumberOfElements;
    }

    public int getRealElementsNumber() {
        List<WebElement> elements = driver.findElements(By.cssSelector(allElementsCss));
        return elements.size();
    }

    public int getRealPriceValue() {
        WebElement totalPriceElement = driver.findElement(By.cssSelector(totalPricePath));
        String result = totalPriceElement.getText();
        result = result.substring(1);
        double expectedTotalPrice = Double.parseDouble(result);
        return (int) expectedTotalPrice;
    }

    public int getExpectedTotalPriceValue() {
        return expectedTotalPriceValue;
    }

    public int getExpectedNumberOfElements() {
        return expectedNumberOfElements;
    }

    public int changeQuantityOfOrderedItem() {
        int elementPrice = getRealPriceValue();
        int totalPrice = elementPrice;

        WebElement increaseArrow = driver.findElement(By.cssSelector(increaseArrowCss));
        for (int i = 0; i < 5; i++) {
            increaseArrow.click();
            totalPrice += elementPrice;
        }
        clickToRefreshPrice();
        return totalPrice;
    }

    private void clickToRefreshPrice() {
        driver.findElement(By.cssSelector(totalPricePath)).click();
    }

    public void cancelOrder() {
        WebElement cancelOrderButton = driver.findElement(By.className(cancelOrderClass));
        cancelOrderButton.click();
    }

}