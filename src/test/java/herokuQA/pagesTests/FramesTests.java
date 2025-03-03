package herokuQA.pagesTests;

import herokuQA.core.BaseTest;
import herokuQA.pages.FramesPage;
import herokuQA.pages.HomePage;
import herokuQA.utils.DataProviders;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class FramesTests extends BaseTest {

    @BeforeMethod
    public void precondition() {
        new HomePage(app.driver, app.wait).nestedFrames();
    }

    @Test
    public void framePositiveTest() {
        new FramesPage(app.driver, app.wait)
                .getListOfFrames();
    }

    @Test
    public void switchToFramePositiveTest() {
        new FramesPage(app.driver, app.wait)
                .switchToFrameByFrameName("frame-top","frame-right")
                .verifyIFrameText("RIGHT");
    }

    @Test (dataProvider = "framesNamePositiveProvider", dataProviderClass = DataProviders.class)
    public void framesNamePositiveProviderTest(String parentFrameName, String childFrameName, String frameText) {
        new FramesPage(app.driver, app.wait)
                .switchToFrameByFrameName(parentFrameName, childFrameName)
                .verifyIFrameText(frameText);
    }
}
