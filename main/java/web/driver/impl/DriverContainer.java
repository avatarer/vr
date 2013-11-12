package web.driver.impl;

import org.openqa.selenium.WebDriver;

/**
 * Created with IntelliJ IDEA.
 * All rights belong to HighWay Company
 * User: Yuriy
 * Date: 27.10.13
 * Time: 13:27
 */
public interface DriverContainer {
    public WebDriver getDriver();

    public void setDriver(WebDriver driver);

    void registerListener(DriverContainerEventListener listener);

    void unregisterListener(DriverContainerEventListener listener);

    void notifyUpdated();
}
