package herokuQA.utils;

import org.testng.annotations.DataProvider;

public class DataProviders {

    @DataProvider
    public Object[][] loginDataPositiveProvider() {
        return new Object[][]{
                {"tomsmith", "SuperSecretPassword!"}
        };
    }

    @DataProvider
    public Object[][] loginDataNegativeProvider() {
        return new Object[][]{
                {"tomsmith", "NoSuperSecretPassword!"},
                {"smithtom", "SuperSecretPassword!"},
                {"smithtom", "NoSuperSecretPassword!"}
        };
    }

    @DataProvider
    public Object[][] framesNamePositiveProvider() {
        return new Object[][]{
                {"frame-top", "frame-left", "LEFT"},
                {"frame-top", "frame-middle", "MIDDLE"},
                {"frame-top", "frame-right", "RIGHT"},
                {"*", "frame-bottom", "BOTTOM"}
        };
    }
}
