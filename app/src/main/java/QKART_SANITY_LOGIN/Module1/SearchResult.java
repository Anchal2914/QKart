package QKART_SANITY_LOGIN.Module1;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchResult {
    WebElement parentElement;

    public SearchResult(WebElement SearchResultElement) {
        this.parentElement = SearchResultElement;
    }

    /*
     * Return title of the parentElement denoting the card content section of a
     * search result
     */
    public String getTitleofResult() {
        String titleOfSearchResult = "";
        // TODO: CRIO_TASK_MODULE_TEST_AUTOMATION - TEST CASE 03: MILESTONE 1
        // Find the element containing the title (product name) of the search result and
        // assign the extract title text to titleOfSearchResult
        WebElement titleEle = parentElement.findElement(
                By.xpath(".//p[@class='MuiTypography-root MuiTypography-body1 css-yg30e6']"));
        titleOfSearchResult = titleEle.getText();
    
        return titleOfSearchResult;
    }

    /*
     * Return Boolean denoting if the open size chart operation was successful
     */
    public Boolean openSizechart() {
        try {
            WebElement sizeChartButtonEle = parentElement.findElement(By.xpath(".//button[text()='Size chart']"));
            sizeChartButtonEle.click();

            Thread.sleep(2000);
            return true;
        } catch (Exception e) {
            System.out.println("Exception while opening Size chart: " + e.getMessage());
            return false;
        }
    }

    /*
     * Return Boolean denoting if the close size chart operation was successful
     */
    public Boolean closeSizeChart(WebDriver driver) {
        try {
            Thread.sleep(2000);
            Actions action = new Actions(driver);
            // Clicking on "ESC" key closes the size chart modal
            action.sendKeys(Keys.ESCAPE);
            action.perform();
            Thread.sleep(2000);
            return true;
        } catch (Exception e) {
            System.out.println("Exception while closing the size chart: " + e.getMessage());
            return false;
        }
    }

    /*
     * Return Boolean based on if the size chart exists
     */
    public Boolean verifySizeChartExists() {
        Boolean status = false;
        try {
            /*
             * Check if the size chart element exists. If it exists, check if the text of
             * the element is "SIZE CHART". If the text "SIZE CHART" matches for the
             * element, set status = true , else set to false
             */
            WebElement sizeChartButtonEle =
                    parentElement.findElement(By.xpath(".//button[text()='Size chart']"));
            if (sizeChartButtonEle.isDisplayed()) {
                String text = sizeChartButtonEle.getText();
                if (text.equalsIgnoreCase("SIZE CHART")) {
                    status = true;
                }
            }
            return status;
        } catch (Exception e) {
            return status;
        }
    }

    /*
     * Return Boolean if the table headers and body of the size chart matches the
     * expected values
     */
    public Boolean validateSizeChartContents(List<String> expectedTableHeaders, List<List<String>> expectedTableBody,
            WebDriver driver) {
        Boolean status = true;
        try {
            /*
             * Locate the table element when the size chart modal is open
             * 
             * Validate that the contents of expectedTableHeaders is present as the table
             * header in the same order
             * 
             * Validate that the contents of expectedTableBody are present in the table body
             * in the same order
             */
            List<WebElement> tableHearderEles = driver.findElements(By.xpath("//table/thead/tr/th"));
            for(int i=0; i<expectedTableHeaders.size(); i++){
                String expectedTableHeaderVal = expectedTableHeaders.get(i);
                WebElement tableHeaderEle = tableHearderEles.get(i);
                String actualTableHeaderVal = tableHeaderEle.getText();
                if(!expectedTableHeaderVal.equals(actualTableHeaderVal)) {
                    status = false;
                }
            }
            for(int i=0; i<expectedTableBody.size(); i++){
                List<String> rowList = expectedTableBody.get(i);

                for(int j=0; j<rowList.size(); j++){
                    String expectedTableBodyVal = rowList.get(j);
                    int rowIndex = i + 1;
                    int coulmnIndex = j + 1;
                    WebElement actualTableBodyEle = driver.findElement(By.xpath("//table/tbody/tr[" + rowIndex + "]/td[" + coulmnIndex + "]"));
                    String actualTableBodyVal = actualTableBodyEle.getText();
                    if(!expectedTableBodyVal.equals(actualTableBodyVal)){
                        status = false;
                    }
                }
            }

            return status;

        } catch (Exception e) {
            System.out.println("Error while validating chart contents");
            return false;
        }
    }

    /*
     * Return Boolean based on if the Size drop down exists
     */
    public Boolean verifyExistenceofSizeDropdown(WebDriver driver) {
        Boolean status = false;
        try {
            WebElement sizeDropdownEle =
                    driver.findElement(By.xpath("//select[@id='uncontrolled-native']"));
            if (sizeDropdownEle.isDisplayed()) {
                    status = true;
                }
            return status;
        } catch (Exception e) {
            return status;
        }
    }
}
