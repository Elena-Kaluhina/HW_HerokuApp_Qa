package herokuQA.pages;

import herokuQA.core.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class FramesPage extends BasePage {

    public FramesPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    @FindBy(tagName = "body")
    List<WebElement> iframes;

    public FramesPage getListOfFrames() {
        if (iframes == null || iframes.isEmpty()){
            System.out.println("No iFrame was found using @FindBy");
        } else {
            System.out.println("Number of iFrames on the page are: [" + iframes.size() + "]");
            for (WebElement iframe : iframes) {
                String iFrameID = iframe.getAttribute("id");
                String iFrameSRC = iframe.getAttribute("src");
                System.out.println("Iframe found ID: [" + (iFrameID != null ? iFrameID : "No ID")
                        + "], SRC: [" + (iFrameSRC != null ? iFrameSRC : "No SRC") + "]");
            }
        }

        return this;
    }

    public FramesPage switchToFrameByFrameName(String parentFrameName, String childFrameName) {

        driver.switchTo().defaultContent(); // выходим из всех фреймов

        // если родительский фрейм существует (не "*"), переключаемся на него
        if (!parentFrameName.equals("*")) {
            driver.switchTo().frame(parentFrameName); // переключаемся в родительский frame
        }
        driver.switchTo().frame(childFrameName); // переключаемся в frame-right

        return this;
    }

    public FramesPage verifyIFrameText(String expectedText) {
        WebElement frameBody = driver.findElement(By.tagName("body"));
        shouldHaveText(frameBody, expectedText, 2000);
        return this;
    }

}
