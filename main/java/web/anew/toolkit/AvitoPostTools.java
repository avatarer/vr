package web.anew.toolkit;

import com.antigate.exception.AntigateException;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import web.anew.data.*;
import web.antigate.AntigateService;
import web.driver.DriverJsService;
import web.logger.InjectLogger;
import web.tools.PageTools;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Created with IntelliJ IDEA.
 * User: IceX
 * Date: 30.10.13
 * Time: 14:56
 * To change this template use File | Settings | File Templates.
 */
@Singleton
public class AvitoPostTools {
    @Inject
    private WebDriver driver;

    @Inject
    private PageTools pageTools;

    @Inject
    private DriverJsService jsService;

    @Inject
    private AntigateService antigateService;

    @InjectLogger
    private Logger logger;

    public void postPhoto(int photoTries, List<String> photo) {
        if ((photo == null) || (photo.size() == 0)) {
            return;
        }
        pageTools.manageTimeout();
        post:
        for (String file : photo) {
            for (int i = 0; i < photoTries; i++) {
                try {
                    final AtomicReference<Boolean> finished = new AtomicReference<>(false);
                    WebElement element = driver.findElement(By.xpath(FormAdvert1Xpath.photoFileInput));
                    element.sendKeys(file);
                    WebDriverWait wait = new WebDriverWait(driver, Timeouts.photoPostTimeoutMs);
                    wait.until(new ExpectedCondition<Object>() {
                        @Override
                        public Object apply(org.openqa.selenium.WebDriver input) {
                            try {
                                if (!input.findElement(By.xpath(FormAdvert1Xpath.photoFileInput)).isEnabled()) {
                                    return null;
                                }
                                WebElement el;
                                try {
                                    el = input.findElement(By.xpath(FormAdvert1Xpath.toMuchPhoto));
                                    if (el.isDisplayed()) {
                                        finished.set(true);
                                    }
                                } catch (NoSuchElementException e) {

                                }
                                return finished;
                            } catch (NoSuchElementException e1) {

                            }

                            return null;
                        }
                    });
                    if (finished.get()) {
                        break post;
                    }
                    break;
                } catch (StaleElementReferenceException e) {
                    logger.warn("photo post", e);
                }
            }
        }
    }

    public List<String> getErrors() {
        pageTools.resetTimeout();
        List<WebElement> elements = driver.findElements(By.xpath(OtherXpath.errorDivXpath));
        List<String> errors = new ArrayList<>();
        for (WebElement element : elements) {
            errors.add(element.getText());
        }
        return errors;
    }

    public void finish1AdvertForm() {
        pageTools.manageTimeout();
        driver.findElement(By.xpath(FormAdvert1Xpath.freeLabel)).click();
        driver.findElement(By.xpath(FormAdvert1Xpath.submit)).click();
    }

    public void doAdvert2Form() throws AntigateException {
        pageTools.manageTimeout();
        driver.findElement(By.xpath(FormAdvert2Xpath.captchaImage));
        jsService.loadNow();
        String captcha_image = pageTools.getImageBase64(driver.findElement(By.xpath(FormAdvert2Xpath.captchaImage)));
        driver.findElement(By.xpath(FormAdvert2Xpath.captchaInput)).sendKeys(antigateService.base64Request(captcha_image));
        driver.findElement(By.xpath(FormAdvert2Xpath.freeLabel)).click();
        driver.findElement(By.xpath(FormAdvert2Xpath.submit)).click();
    }

    public void setHeader(String header, String value) {
        if (value == null) {
            return;
        }
        ((JavascriptExecutor) driver).executeScript("return atSetHeader(arguments[0],arguments[1]);", header, value);
    }
}
