package web.driver;

/**
 * Created with IntelliJ IDEA.
 * User: IceX
 * Date: 30.10.13
 * Time: 15:12
 * To change this template use File | Settings | File Templates.
 */
public class CachedJsLoader implements JsLoader {
    private final String js;

    public CachedJsLoader(String js) {
        this.js = js;
    }

    @Override
    public String getScript() {
        return js;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
