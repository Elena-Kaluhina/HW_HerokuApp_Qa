package herokuQA.core;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.io.IOException;
import java.lang.reflect.Method;

public class BaseTest {
 protected final ApplicationManager app = new ApplicationManager();

 Logger logger = LoggerFactory.getLogger(BaseTest.class);


    @BeforeMethod
    public void logTestStart(Method m) {
        logger.info("Test is started: [" + m.getName() + "]");
        app.init();
    }

    @AfterMethod
    public void tearDownAll(Method method, ITestResult result) {
        if (result.isSuccess()){
            logger.info("Test is Passed: [" + method.getName() + "]");
        }else {
            logger.error("Test is Failed: [" + method.getName() + "]");
        }
        app.stop();
    }
}

