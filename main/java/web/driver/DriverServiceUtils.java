package web.driver;

/**
 * Created with IntelliJ IDEA.
 * All rights belong to HighWay Company
 * User: Yuriy
 * Date: 27.10.13
 * Time: 14:17
 */
public class DriverServiceUtils {

    public static JsLoader jsLoaderFromString(final String js) {
        return new JsLoader() {
            @Override
            public String getScript() {
                return js;
            }
        };
    }
}
