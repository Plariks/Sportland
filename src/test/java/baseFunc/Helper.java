package baseFunc;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Helper {

    BaseFunc baseFunc;

    private final By FILTER_NAMES = By.xpath("//fieldset/ol/li/label");
    private final By BRANDE_APPEARANCE = By.xpath("//div/fieldset/legend[contains(text(), Brands)]");
    private final By ITEM_AMOUNT_ON_PAGE = By.xpath("//p[@class='spodb-product-card__title']");


    public Helper(BaseFunc baseFunc) {
        this.baseFunc = baseFunc;
    }

    public void selectFilters(List<String> filterNames) throws InterruptedException {
        List<WebElement> filters = baseFunc.getAllElements(FILTER_NAMES);
        for (int i = 0; i < filters.size(); i++) {
            for (int j = 0; j < filterNames.size(); j++) {

                if (filters.get(i).getText().contains(filterNames.get(j))) {
                    filters.get(i).click();
                    TimeUnit.SECONDS.sleep(4);
                    filters = baseFunc.getAllElements(FILTER_NAMES);

                    break;
                }

            }

        }

    }

}
