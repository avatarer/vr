package web.logger;

import com.google.inject.AbstractModule;
import com.google.inject.matcher.Matchers;

/**
 * Created with IntelliJ IDEA.
 * User: IceX
 * Date: 29.10.13
 * Time: 14:07
 * To change this template use File | Settings | File Templates.
 */
public class LoggerModule extends AbstractModule {

    @Override
    protected void configure() {
        bindListener(Matchers.any(), new Slf4jTypeListener());
    }
}
