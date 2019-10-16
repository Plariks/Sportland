package stepDefs;

import baseFunc.BaseFunc;
import baseFunc.Helper;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebElement;
import pages.ApaviPage;
import pages.HomePage;
import pages.ProductPage;

import java.io.IOException;
import java.util.List;

public class SportlandStepDefs {

    private BaseFunc baseFunc = new BaseFunc();
    private HomePage homePage = new HomePage(baseFunc);
    private ProductPage productPage;
    private ApaviPage apaviPage = new ApaviPage(baseFunc);
    private Helper helper = new Helper(baseFunc);

    @Given("shop {string} webpage")
    public void main_test_page(String webPageName){
        baseFunc.openPage(webPageName);
    }

    @When("we click on side menu {string} tab")
    public void side_menu_tabs(String menuTabName){
        homePage.sideTabMenuClick(menuTabName);
    }

    @Then("we move mouse over zeni tab")
    public void check_opened_page() {
        productPage = new ProductPage(baseFunc);
        productPage.moveOverProductTab();
    }

    @Then("choose from apavi list {string}")
    public void take_something_from_category(String category) {
        productPage.letsfindNeededProduct(category);
    }

    @When("sort by {string}")
    public void sort_by_something(String sorting) throws InterruptedException {
        apaviPage.sortByCategory(sorting);
    }

    @Then("select filters:")
    public void select_filtering(List<String> filters) throws InterruptedException {
        helper.selectFilters(filters);
    }


    @Then("check that all items has {string} brand")
    public void check_brand(String brandName) throws InterruptedException {
        apaviPage.finBrandItems(brandName);
    }

    @When("check that all items is on sale")
    public void check_sales() {
        apaviPage.checkSalesItems();
    }

    @Then("save information about shoes below 50eur in txt file")
    public void save_info_in_text() throws IOException {
        apaviPage.removeEuroAndCompare();
    }

//    @Then("select filters: {string}")
//    public void click_on_filter(String filterName) {
//        apaviPage.clickOnNeedeFilter(filterName);
//    }
}
