package web.driver.impl;

import org.apache.commons.configuration.Configuration;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import web.driver.DriverConstants;

import java.io.File;

/**
 * Created with IntelliJ IDEA.
 * All rights belong to HighWay Company
 * User: Yuriy
 * Date: 27.10.13
 * Time: 13:30
 */
public class ChromeFactory implements DriverFactory {

    private File getExecutable() {
        return new File("chromedriver.exe");
    }

    DesiredCapabilities createCapabilities(Configuration config) {
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        if (config != null) {
            if (config.containsKey(DriverConstants.PROXY_IP) && config.containsKey(DriverConstants.PROXY_PORT)) {
                Proxy proxy = new Proxy();
                String sProxy = config.getString(DriverConstants.PROXY_IP) + ":" + config.getString(DriverConstants.PROXY_PORT);
                proxy.setHttpProxy(sProxy).setFtpProxy(sProxy).setSslProxy(sProxy);
                capabilities.setCapability(CapabilityType.PROXY, proxy);
            }
        }
        return capabilities;
    }

    @Override
    public WebDriver createDriver(Configuration config) {
        ChromeDriverService.Builder builder = new ChromeDriverService.Builder();
        builder.usingAnyFreePort();
        builder.usingChromeDriverExecutable(getExecutable());
        ChromeDriver chromeDriver = new ChromeDriver(builder.build(), createCapabilities(config));
        return chromeDriver;
    }
}
