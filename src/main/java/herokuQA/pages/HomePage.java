package herokuQA.pages;

import herokuQA.core.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage extends BasePage {

    public HomePage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    @FindBy(css = "a[href='/login']")
    WebElement formAuthentication;

    public LoginPage formAuthentication() {
        clickWithJS(formAuthentication, 0, 500);
        return new LoginPage(driver, wait);
    }

    @FindBy(xpath = "//a[@href='/nested_frames']")
    WebElement nested_frames;

    public FramesPage nestedFrames() {
        click(nested_frames);
        return new FramesPage(driver, wait);
    }

    @FindBy(css = "a[href='/windows']")
    WebElement multipleWidowsLink;

    public MultipleWindowsPage multipleWindows() {
        clickWithJS(multipleWidowsLink, 0, 500);
        return new MultipleWindowsPage(driver, wait);
    }

    @FindBy(css = "a[href='/dropdown']")
    WebElement dropDownLink;

    public DropdownPage dropDown() {
        click(dropDownLink);
        return new DropdownPage(driver, wait);
    }

    @FindBy(css = "a[href='/context_menu']")
    WebElement context_menu;

    public ContextMenuPage contextMenu() {
        click(context_menu);
        return new ContextMenuPage(driver, wait);
    }

    @FindBy(css = "a[href='/checkboxes']")
    WebElement checkboxes;

    public CheckBoxesPage checkboxes() {
        click(checkboxes);
        return new CheckBoxesPage(driver, wait);
    }

    @FindBy (css = "a[href='/add_remove_elements/']")
    WebElement add_remove_elements;

    public AddRemoveElementsPage addRemoveElements() {
        click(add_remove_elements);
        return new AddRemoveElementsPage(driver, wait);
    }

    @FindBy (css = "a[href='/hovers']")
    WebElement hovers;

    public HoversPage clickHoversLink() {
        clickWithJS(hovers, 0, 500);
        return new HoversPage(driver, wait);
    }
}
