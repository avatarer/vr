package web.tools;

import com.google.inject.Singleton;
import js.JsClasspathPointer;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import web.driver.CachedJsLoader;
import web.driver.JsLoader;
import web.logger.InjectLogger;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created with IntelliJ IDEA.
 * User: IceX
 * Date: 30.10.13
 * Time: 15:10
 * To change this template use File | Settings | File Templates.
 */
@Singleton
public class JsTools {
    @InjectLogger
    private Logger logger;

    public JsLoader createJsLoader(String scriptId) {
        InputStream js = JsClasspathPointer.class.getResourceAsStream("/js/" + scriptId + ".js");
        if (js == null) {
            logger.error("js stream with id {} is null", scriptId);
            throw new IllegalArgumentException("js stream is null");
        }
        try {
            CachedJsLoader jsLoader = new CachedJsLoader(IOUtils.toString(js));
            return jsLoader;
        } catch (IOException e) {
            logger.error("io fail", e);
            throw new RuntimeException(e);
        }
    }
}
