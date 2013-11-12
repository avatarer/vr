package web.anew.toolkit;

import com.antigate.exception.AntigateException;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.openqa.selenium.*;
import org.slf4j.Logger;
import web.anew.data.*;
import web.antigate.AntigateService;
import web.driver.DriverJsService;
import web.logger.InjectLogger;
import web.tools.PageTools;

import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA.
 * User: IceX
 * Date: 30.10.13
 * Time: 14:44
 * To change this template use File | Settings | File Templates.
 */
@Singleton
public class AvitoPageTools {
    @InjectLogger
    private Logger logger;

    @Inject
    private WebDriver driver;

    @Inject
    private DriverJsService jsService;

    @Inject
    private AntigateService antigateService;

    @Inject
    private PageTools pageTools;

    public void login(String login, String password) {
        pageTools.manageTimeout();
        driver.get(PageLinks.login);
        driver.findElement(By.xpath(FormLoginXpath.loginInput)).sendKeys(login);
        driver.findElement(By.xpath(FormLoginXpath.passwordInput)).sendKeys(password);
        driver.findElement(By.xpath(FormLoginXpath.submit)).click();
    }

    public void gotoItemAdd() {
        driver.get("http://www.avito.ru/additem");
    }
}
