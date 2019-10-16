package pages;

import baseFunc.BaseFunc;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ApaviPage {


    BaseFunc baseFunc;

    private Double cleanEur;


    private final By DROP_MENU_FIRST_FOR_SORTING = By.xpath("//div/fieldset/div/select");
    private final By SORTING_BY_CATEGORY = By.xpath("//div/fieldset/div/select/option");
    private final By CONTAINS_PERCENTAGE = By.xpath("//div[@class='spodb-products-grid']/div/a/p[@class='spodb-product-card__percentage']");
    private final By ITEM_AMOUNT_ON_PAGE = By.xpath("//p[@class='spodb-product-card__title']");
    private final By COST_WITH_SALE = By.xpath("//div/p[@class='spodb-product-card__new-price']");
    private final By PRODUCT_INFORMATION = By.xpath(".//a[@class = 'spodb-product-card']");

    public ApaviPage(BaseFunc baseFunc) {
        this.baseFunc = baseFunc;
    }

    public void sortByCategory(String sortingName) throws InterruptedException {
        baseFunc.getElement(DROP_MENU_FIRST_FOR_SORTING).click();
        List<WebElement> sortingFromDrop = baseFunc.getAllElements(SORTING_BY_CATEGORY);
        for (int i = 0; i < sortingFromDrop.size(); i++) {
            if (sortingFromDrop.get(i).getText().equals(sortingName)) {
                sortingFromDrop.get(i).click();
                TimeUnit.SECONDS.sleep(4);
            }
        }
    }

    public void finBrandItems(String brandItems) throws InterruptedException {
        baseFunc.waitePageLoader();
//        Thread.sleep(5);
        brandItems = brandItems.toUpperCase();
        baseFunc.waitJsExecution(ITEM_AMOUNT_ON_PAGE);
        List<WebElement> checkingBrandNames = baseFunc.getAllElements(ITEM_AMOUNT_ON_PAGE);
        for (WebElement item : checkingBrandNames) {

            Assertions.assertTrue(item.getText().contains(brandItems));
        }
    }

    public void checkSalesItems() {
        List<WebElement> salesPercente = baseFunc.getAllElements(CONTAINS_PERCENTAGE);
        for (WebElement percente : salesPercente) {
            Assertions.assertTrue(percente.getText().contains("%"));

        }
    }

//    public void collectInformationTxt(Double cleanEur) throws IOException {
//        List<WebElement> information = baseFunc.getAllElements(PRODUCT_INFORMATION);
//        File file = new File("AllInfoForBoots.txt");
//        FileWriter fw = new FileWriter(file);
//        for (WebElement info : information) {
//            fw.write(info.getText() + "\r\n");
//        }
//        fw.close();
//    }

//    public void listFindAllInfo() {
//        List<WebElement> information = baseFunc.getAllElements(PRODUCT_INFORMATION);
//        for (int i = 0; i < information.size(); i++) {
//            List<WebElement>
//
//        }
//    }



    public void removeEuroAndCompare() throws IOException {
        List<WebElement> allMoneys = baseFunc.getAllElements(COST_WITH_SALE);
        File file = new File("AllInfoForBoots.txt");
        FileWriter fw = new FileWriter(file);
        for (int i = 0; i < allMoneys.size(); i++) {
            String withEur = allMoneys.get(i).getText();
            String withoutEur = withEur.replace("€", "");
            cleanEur = Double.parseDouble(withoutEur);
            if (cleanEur <= 60) {

                    fw.write(cleanEur + "\r\n");
            } else {continue;}
        } fw.close();
    }

//    public void clickOnNeedeFilter(String filteredItemName) {
//        baseFunc.waitForJsAgain();
//        List<WebElement> filterClick = baseFunc.getAllElements(FILTER_NAMES);
//        for (int i = 0; i < filterClick.size(); i++) {
//            if (!filterClick.get(i).getText().equals(filteredItemName)) {
//                continue;
//            } else {
//                filterClick.get(i).click();
//                baseFunc.waitForJsAgain();
//                break;
//            }
//        }
//    }
}
