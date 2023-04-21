package QKART_SANITY_LOGIN.Module1;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Home {
    RemoteWebDriver driver;
    String url = "https://crio-qkart-frontend-qa.vercel.app";

    public Home(RemoteWebDriver driver) {
        this.driver = driver;
    }

    public void navigateToHome() {
        if (!this.driver.getCurrentUrl().equals(this.url)) {
            this.driver.get(this.url);
              }
    }

    public Boolean PerformLogout() throws InterruptedException {
        try {
            // Find and click on the Logout Button
            WebElement logout_button = driver.findElement(By.className("MuiButton-text"));
            logout_button.click();

            // SLEEP_STMT_10: Wait for Logout to complete
            // Wait for Logout to Complete
            Thread.sleep(3000);

            return true;
        } catch (Exception e) {
            // Error while logout
            return false;
        }
    }

    /*
     * Returns Boolean if searching for the given product name occurs without any
     * errors
     */
    public Boolean searchForProduct(String product) {
        try {
            WebElement searchBoxElement = driver.findElement(By.xpath(
                    "//div[@class='MuiFormControl-root MuiTextField-root search-desktop css-i44wyl']/div/input"));
            searchBoxElement.clear();
            searchBoxElement.sendKeys(product);
            Thread.sleep(5000);
            //driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
            // WebDriverWait wait = new WebDriverWait(driver, 30);
            // wait.until(
            //   ExpectedConditions.or(
            //     ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(), '"+product+"')]")),
            //        ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()=' No products found ']"))
            //   ) 
            // );
            return true;
        } catch (Exception e) {
            System.out.println("Error while searching for a product: " + e.getMessage());
            return false;
        }
    }

    /*
     * Returns Array of Web Elements that are search results and return the same
     */
    public List<WebElement> getSearchResults() {
        List<WebElement> searchResults = new ArrayList<WebElement>() {
        };
        try {
            searchResults = driver
                    .findElements(By.xpath("//div[@class='MuiCardContent-root css-1qw96cp']"));
            return searchResults;
        } catch (Exception e) {
            System.out.println("There were no search results: " + e.getMessage());
            return searchResults;

        }
    }

    /*
     * Returns Boolean based on if the "No products found" text is displayed
     */
    public Boolean isNoResultFound() {
        Boolean status = false;
        try {
            WebElement noProductFoundEle =
                    driver.findElement(By.xpath("//h4[text()=' No products found ']"));
            if (noProductFoundEle.isDisplayed()) {
                status = true;
    }
    return status;
        } catch (Exception e) {
            return status;
        }
    }

    /*
     * Return Boolean if add product to cart is successful
     */
    public Boolean addProductToCart(String productName) {
        try {
            /*
             * Iterate through each product on the page to find the WebElement corresponding
             * to the matching productName
             * 
             * Click on the "ADD TO CART" button for that element
             * 
             * Return true if these operations succeeds
             */
            List<WebElement> productTitleEles = driver.findElements(
                    By.xpath("//p[@class='MuiTypography-root MuiTypography-body1 css-yg30e6']"));
            List<WebElement> productAddToCartEles =
                    driver.findElements(By.xpath("//button[text()='Add to cart']"));

            for (int i = 0; i < productTitleEles.size(); i++) {
                WebElement productTitleElement = productTitleEles.get(i);
                String productTitleText = productTitleElement.getText();
                if (productTitleText.equals(productName)) {
                    WebElement productAddToCarElement = productAddToCartEles.get(i);
                    productAddToCarElement.click();
                }
            }

            System.out.println("Unable to find the given product");
            return false;
        } catch (Exception e) {
            System.out.println("Exception while performing add to cart: " + e.getMessage());
            return false;
        }
    }

    /*
     * Return Boolean denoting the status of clicking on the checkout button
     */
    public Boolean clickCheckout() {
        Boolean status = false;
        try {
            WebElement checkoutElement =
                    driver.findElement(By.xpath("//button[text()='Checkout']"));
            checkoutElement.click();

            return status;
        } catch (Exception e) {
            System.out.println("Exception while clicking on Checkout: " + e.getMessage());
            return status;
        }
    }

    /*
     * Return Boolean denoting the status of change quantity of product in cart
     * operation
     */
    public Boolean changeProductQuantityinCart(String productName, int quantity) {
        try {
            List<WebElement> titleElemets =
                    driver.findElements(By.xpath("//div[@class='MuiBox-root css-1gjj37g']/div[1]"));
            List<WebElement> actualQuantityElements =
                    driver.findElements(By.xpath("//*[@class='css-u4p24i']/div[1]"));
            List<WebElement> plusButtonElements =
                    driver.findElements(By.xpath("//*[@class='css-u4p24i']/button[2]"));
            List<WebElement> minusButtonElements =
                    driver.findElements(By.xpath("//*[@class='css-u4p24i']/button[1]"));

            for (int i=0; i<titleElemets.size(); i++) {
                WebElement productNameElement = titleElemets.get(i);
                String actualProductName = productNameElement.getText();

                if (productName.equals(actualProductName)) {

                    while (true) {
                        WebElement actualQuantityElement = actualQuantityElements.get(i);
                        String actualQuantityText = actualQuantityElement.getText();
                        int actualQuantity = Integer.parseInt(actualQuantityText);
                        
                        if(quantity > actualQuantity) {
                            WebElement plusButtonElement = plusButtonElements.get(i);
                            plusButtonElement.click();
                            //Thread.sleep(2000);
                            String actQuantityText = driver.findElement(By.xpath("//*[@id='root']/div/div/div[3]/div[2]/div/div[1]/div/div[2]/div[2]/div[1]/div")).getText();
                            int actQuantity = Integer.parseInt(actQuantityText);
                            WebDriverWait wait = new WebDriverWait(driver, 3);
                            wait.until(ExpectedConditions.textToBe(
                            By.xpath("//*[@id='root']/div/div/div[3]/div[2]/div/div[1]/div/div[2]/div[2]/div[1]/div"),
                            String.valueOf(actQuantity + 1)));
                        } else if (quantity < actualQuantity) {
                            WebElement minusButtonElement = minusButtonElements.get(i);
                            minusButtonElement.click();
                            //Thread.sleep(2000);
                            String actQuantityText = driver.findElement(By.xpath("//*[@id='root']/div/div/div[3]/div[2]/div/div[1]/div/div[2]/div[2]/div[1]/div")).getText();
                            int actQuantity = Integer.parseInt(actQuantityText);
                            WebDriverWait wait = new WebDriverWait(driver, 3);
                            wait.until(ExpectedConditions.textToBe(
                            By.xpath("//*[@id='root']/div/div/div[3]/div[2]/div/div[1]/div/div[2]/div[2]/div[1]/div"),
                            String.valueOf(actQuantity - 1)));
                        } else if (quantity == actualQuantity) {
                            break;
                        }

                    }
                }
                

            }        

            return false;
        } catch (Exception e) {
            if (quantity == 0)
                return true;
            System.out.println("exception occurred when updating cart: " + e.getMessage());
            return false;
        }
    }

    /*
     * Return Boolean denoting if the cart contains items as expected
     */
    public Boolean verifyCartContents(List<String> expectedCartContents) {
        try {
            List<WebElement> cartItemsElements = driver.findElements(By.xpath("//div[@class='MuiBox-root css-1gjj37g']"));
            
            for(WebElement element : cartItemsElements){
                String actualCartContents = element.getText();
                if(!expectedCartContents.equals(actualCartContents)) {
                    boolean isPresent = false;
                }
            }

            return true;

        } catch (Exception e) {
            System.out.println("Exception while verifying cart contents: " + e.getMessage());
            return false;
        }
    }
}
