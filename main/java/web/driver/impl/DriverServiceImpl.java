package web.driver.impl;

import com.google.inject.Inject;
import org.apache.commons.configuration.Configuration;
import org.openqa.selenium.WebDriver;
import web.WebServiceException;
import web.driver.DriverService;

/**
 * Created with IntelliJ IDEA.
 * All rights belong to HighWay Company
 * User: Yuriy
 * Date: 27.10.13
 * Time: 13:45
 */
public class DriverServiceImpl implements DriverService {
    private Configuration configuration;
    @Inject
    private DriverContainer container;
    @Inject
    private DriverFactory driverFactory;

    @Override
    public void appendConfiguration(Configuration config) {
        configuration = config;
    }

    @Override
    public void start() throws WebServiceException {
        WebDriver driver = driverFactory.createDriver(configuration);
        container.setDriver(driver);
        container.notifyUpdated();
    }

    @Override
    public void stop() {
        WebDriver driver = container.getDriver();
        driver.quit();
        container.setDriver(null);
    }

    @Override
    public void clear() throws WebServiceException {
        WebDriver driver = container.getDriver();
        driver.manage().deleteAllCookies();
    }

    @Override
    protected void finalize() throws Throwable {
        stop();
    }
}
