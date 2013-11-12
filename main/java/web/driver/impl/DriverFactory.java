package web.driver.impl;

import com.google.inject.ImplementedBy;
import org.apache.commons.configuration.Configuration;
import org.openqa.selenium.WebDriver;


/**
 * Created with IntelliJ IDEA.
 * All rights belong to HighWay Company
 * User: Yuriy
 * Date: 27.10.13
 * Time: 12:51
 */
@ImplementedBy(ChromeFactory.class)
public interface DriverFactory {
    WebDriver createDriver(Configuration config);
}
