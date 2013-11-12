package web.driver;

import com.google.inject.ImplementedBy;
import web.driver.impl.DriverJsServiceImpl;

/**
 * Created with IntelliJ IDEA.
 * All rights belong to HighWay Company
 * User: Yuriy
 * Date: 27.10.13
 * Time: 12:46
 */
@ImplementedBy(DriverJsServiceImpl.class)
public interface DriverJsService {

    void loadNow();

    void inject(JsLoader loader);

    void remove(JsLoader loader);
}
