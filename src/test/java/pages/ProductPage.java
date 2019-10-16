package pages;

import baseFunc.BaseFunc;
import io.cucumber.datatable.dependency.com.fasterxml.jackson.databind.ser.Serializers;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ProductPage {


    private BaseFunc baseFunc;
    private final By TOP_TAB_NAMES_OVER = By.xpath("//nav/div/ul/li/a[contains(text(),'Zēni')]");
    private final By ZENI_PRODUCT_CATEGORY_NAME = By.xpath("//nav/div/ul/li/a[contains(text(),'Zēni')]/following-sibling::ul/li/ul/li/ul/li/a[contains(text(), 'Apavi')]/following-sibling::ul/li/a");


    List<WebElement> categoryUnderTop;


    public ProductPage(BaseFunc baseFunc) {
        this.baseFunc = baseFunc;
    }

    public void moveOverProductTab() {
        baseFunc.moveOverElement(TOP_TAB_NAMES_OVER);
    }

    public void letsfindNeededProduct(String kategory) {
        categoryUnderTop = baseFunc.getAllElements(ZENI_PRODUCT_CATEGORY_NAME);
        for (int i = 0; i < categoryUnderTop.size(); i++) {
            if (!categoryUnderTop.get(i).getText().equals(kategory)) { continue;
            } else { categoryUnderTop.get(i).click(); break; }
        }
    }
}
