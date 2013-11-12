package web.driver.impl;

import com.google.inject.Inject;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;
import org.openqa.selenium.support.events.WebDriverEventListener;
import web.driver.DriverEventService;
import web.driver.DriverJsService;
import web.driver.JsLoader;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * All rights belong to HighWay Company
 * User: Yuriy
 * Date: 27.10.13
 * Time: 14:09
 */
public class DriverJsServiceImpl implements DriverJsService {
    private final Map<JsLoader, JsInjector> listenerMap = new HashMap<>();

    private static void loadScript(WebDriver driver, JsLoader loader) {
        ((JavascriptExecutor) driver).executeScript(loader.getScript());
    }

    @Inject
    DriverEventService eventService;

    @Inject
    WebDriver driver;

    private class JsInjector extends AbstractWebDriverEventListener {
        private final JsLoader jsLoader;

        private JsInjector(JsLoader jsLoader) {
            this.jsLoader = jsLoader;
        }

        @Override
        public void afterNavigateTo(String url, WebDriver driver) {
            loadScript(driver, jsLoader);
        }
    }

    @Override
    public void loadNow() {
        for (JsLoader jsLoader : listenerMap.keySet()) {
            loadScript(driver, jsLoader);
        }
    }

    @Override
    public void inject(JsLoader loader) {
        if (listenerMap.containsKey(loader)) {
            return;
        }
        JsInjector jsInjector = new JsInjector(loader);
        eventService.register(jsInjector);
        listenerMap.put(loader, jsInjector);
    }

    @Override
    public void remove(JsLoader loader) {
        JsInjector jsInjector = listenerMap.get(loader);
        if (jsInjector == null) {
            return;
        }
        eventService.unregister(jsInjector);
        listenerMap.remove(loader);
    }
}
