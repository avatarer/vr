package web.driver.impl;

import com.google.inject.Inject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.events.WebDriverEventListener;
import web.driver.DriverEventService;

/**
 * Created with IntelliJ IDEA.
 * All rights belong to HighWay Company
 * User: Yuriy
 * Date: 27.10.13
 * Time: 13:54
 */
public class DriverEventServiceImpl implements DriverEventService {
    DriverContainer container;

    @Inject
    public DriverEventServiceImpl(DriverContainer container) {
        container.registerListener(new DriverEvent());
        this.container = container;
    }

    private class DriverEvent implements DriverContainerEventListener {
        @Override
        public void updated(DriverContainer container) {
            WebDriver driver = container.getDriver();
            if (!(driver instanceof EventFiringWebDriver)) {
                EventFiringWebDriver webDriver = new EventFiringWebDriver(driver);
                container.setDriver(webDriver);
            }
        }
    }

    private EventFiringWebDriver convertDriver(WebDriver driver) {
        if (!(driver instanceof EventFiringWebDriver)) {
            throw new IllegalStateException("Driver is not instance of EventFiringWebDriver");
        }
        return (EventFiringWebDriver) driver;
    }

    @Override
    public void register(WebDriverEventListener listener) {
        EventFiringWebDriver driver = convertDriver(container.getDriver());
        driver.register(listener);
    }

    @Override
    public void unregister(WebDriverEventListener listener) {
        EventFiringWebDriver driver = convertDriver(container.getDriver());
        driver.unregister(listener);
    }
}
