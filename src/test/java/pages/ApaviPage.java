package pages;

import baseFunc.BaseFunc;
import org.json.JSONArray;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.json.JSONObject;

public class ApaviPage {


    BaseFunc baseFunc;

    private Double cleanEur;

    private File file;
    private FileWriter fw;

    private final By DROP_MENU_FIRST_FOR_SORTING = By.xpath("//div/fieldset/div/select");
    private final By SORTING_BY_CATEGORY = By.xpath("//div/fieldset/div/select/option");
    private final By CONTAINS_PERCENTAGE = By.xpath("//div[@class='spodb-products-grid']/div/a/p[@class='spodb-product-card__percentage']");
    private final By ITEM_AMOUNT_ON_PAGE = By.xpath("//p[@class='spodb-product-card__title']");
    private final By COST_WITH_SALE = By.xpath("//div/p[@class='spodb-product-card__new-price']");
    private final By PRODUCT_INFORMATION = By.xpath(".//a[@class = 'spodb-product-card']");

    private List<WebElement> allMoneys;
    private List<WebElement> allInfo;


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


    public void removeEuroAndCompare() throws IOException {
        allInfo = baseFunc.getAllElements(PRODUCT_INFORMATION);
        for (int i = 0; i < allInfo.size(); i++) {
            allMoneys = baseFunc.getAllElements(COST_WITH_SALE);
            file = new File("AllInfoForBoots.txt");
            fw = new FileWriter(file);
            for (i = 0; i < allMoneys.size(); i++) {
                String withEur = allMoneys.get(i).getText();
                String withoutEur = withEur.replace("€", "");
                cleanEur = Double.parseDouble(withoutEur);
                if (cleanEur <= 50) {

                    fw.write(allInfo.get(i).getText() + "\r\n");
                }
            }
            fw.close();
        }
    }

    public void writeJsonSimpleDemo() throws Exception {
        JSONObject objectOnce = new JSONObject();
        JSONArray arrayOnce = new JSONArray();
        file = new File("C:/Users/694039/Desktop/sportlandhome/Sportland/apaviOnce.json");
        for (int i = 0; i < allInfo.size(); i++) {
            arrayOnce.put(allInfo.get(i).getText());
        }
        objectOnce.put("items", arrayOnce);
        fw = new FileWriter(file);
        fw.write(objectOnce.toString());
        fw.flush();
    }
}