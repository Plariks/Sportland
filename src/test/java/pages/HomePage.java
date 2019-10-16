package pages;

import baseFunc.BaseFunc;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;


public class HomePage {

    private BaseFunc baseFunc;

    private final By SIDE_MENU_BUTTON = By.xpath("//button[contains(@class, 'menu-trigger')]");
    private final By SIDE_MENU_TABS = By.xpath("//ul[@id='menu-primary-menu']/li");
    private final By SIDE_MENU_IS_OPPENED = By.xpath("//ul[@id='menu-primary-menu']/li[contains(@style, 'scaleX(1) scaleY(1)')]");


    public HomePage(BaseFunc baseFunc){
        this.baseFunc = baseFunc;
    }

    public void sideMenuClick() {
        baseFunc.getElement(SIDE_MENU_BUTTON).click();

    }

    public void sideTabMenuClick(String sideTabName) {
        sideMenuClick();
        List<WebElement> listOfSideMenuTabs = baseFunc.getAllElements(SIDE_MENU_TABS);
        for (int i = 0; i < listOfSideMenuTabs.size(); i++) {
            if (!listOfSideMenuTabs.get(i).getText().contains(sideTabName)) {
                continue;
            } else {listOfSideMenuTabs.get(i).click(); break;}


        }
    }
}
