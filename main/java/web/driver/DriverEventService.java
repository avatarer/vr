package web.driver;

import com.google.inject.ImplementedBy;
import org.openqa.selenium.support.events.WebDriverEventListener;
import web.driver.impl.DriverEventServiceImpl;

/**
 * Created with IntelliJ IDEA.
 * All rights belong to HighWay Company
 * User: Yuriy
 * Date: 27.10.13
 * Time: 14:00
 */
@ImplementedBy(DriverEventServiceImpl.class)
public interface DriverEventService {

    void register(WebDriverEventListener listener);

    void unregister(WebDriverEventListener listener);
}
