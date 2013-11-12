package web.tools;

import com.google.inject.Inject;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import web.logger.InjectLogger;
import web.tools.data.Timeouts;

import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA.
 * User: IceX
 * Date: 30.10.13
 * Time: 15:07
 * To change this template use File | Settings | File Templates.
 */
public class PageTools {
    @Inject
    private WebDriver driver;

    @InjectLogger
    private Logger logger;

    public String getImageBase64(WebElement element) {
        return ((JavascriptExecutor) driver).executeScript("return getBase64Image(arguments[0]);", element).toString();
    }

    public void manageTimeout() {
        driver.manage().timeouts().implicitlyWait(Timeouts.implicitDriverTimeout, TimeUnit.SECONDS);
    }

    public void resetTimeout() {
        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
    }

    public void sleep(int timeMs) {
        try {
            Thread.sleep(timeMs);
        } catch (InterruptedException e) {
            logger.error("interrupted", e);
            if (Thread.currentThread().isInterrupted()) {
                throw new RuntimeException(e);
            }
        }
    }
}
