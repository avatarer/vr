package web.driver;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;
import org.openqa.selenium.WebDriver;
import web.driver.impl.DriverContainer;
import web.driver.impl.DriverContainerAndProxyProvider;
import web.driver.impl.DriverEventServiceImpl;
import web.driver.impl.DriverFactory;

/**
 * Created with IntelliJ IDEA.
 * All rights belong to HighWay Company
 * User: Yuriy
 * Date: 27.10.13
 * Time: 12:12
 */
public class DriverModule extends AbstractModule {

    @Override
    protected void configure() {
        DriverContainerAndProxyProvider driverContainerAndProxyProvider = new DriverContainerAndProxyProvider();
        bind(DriverContainer.class).toInstance(driverContainerAndProxyProvider);
        bind(WebDriver.class).toProvider(driverContainerAndProxyProvider);
        bind(DriverFactory.class).in(Scopes.SINGLETON);
        bind(DriverJsService.class).in(Scopes.SINGLETON);
        bind(DriverService.class).asEagerSingleton();
        bind(DriverEventService.class).to(DriverEventServiceImpl.class).asEagerSingleton();
    }
}
