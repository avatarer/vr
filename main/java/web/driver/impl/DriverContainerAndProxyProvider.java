package web.driver.impl;

import com.google.inject.Inject;
import com.google.inject.Provider;
import org.apache.commons.proxy.ObjectProvider;
import org.apache.commons.proxy.ProxyFactory;
import org.apache.commons.proxy.factory.cglib.CglibProxyFactory;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.util.HashSet;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * All rights belong to HighWay Company
 * User: Yuriy
 * Date: 27.10.13
 * Time: 12:53
 */

public class DriverContainerAndProxyProvider implements Provider<WebDriver>, DriverContainer {
    private final WebDriver proxy;
    private WebDriver driver;
    ProxyFactory factory = new CglibProxyFactory();
    private Set<DriverContainerEventListener> listenerSet = new HashSet<>();

    private class Delegator implements ObjectProvider {
        @Override
        public Object getObject() {
            return driver;
        }
    }

    public DriverContainerAndProxyProvider() {
        Class classes[] = {WebDriver.class, JavascriptExecutor.class, TakesScreenshot.class};
        proxy = (WebDriver) factory.createDelegatorProxy(new Delegator(), classes);
    }

    @Override
    public WebDriver getDriver() {
        return driver;
    }

    @Override
    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }

    @Override
    public void registerListener(DriverContainerEventListener listener) {
        listenerSet.add(listener);
    }

    @Override
    public void unregisterListener(DriverContainerEventListener listener) {
        listenerSet.remove(listener);
    }

    @Override
    public void notifyUpdated() {
        for (DriverContainerEventListener listener : listenerSet) {
            listener.updated(this);
        }
    }

    @Override
    public WebDriver get() {
        return proxy;
    }
}
