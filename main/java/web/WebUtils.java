package web;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import web.antigate.AntigateModule;
import web.exc.ExcModule;
import web.driver.DriverModule;
import web.logger.LoggerModule;
import web.validator.ValidatorModule;

/**
 * Created with IntelliJ IDEA.
 * All rights belong to HighWay Company
 * User: Yuriy
 * Date: 28.10.13
 * Time: 6:41
 */
public class WebUtils {
    public static Injector createInjector() {
        AbstractModule internal[] = {new DriverModule(), new ExcModule(), new AntigateModule(), new ValidatorModule(), new LoggerModule()};
        return Guice.createInjector(internal);
    }
}
