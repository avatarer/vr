package web.exc;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;

/**
 * Created with IntelliJ IDEA.
 * All rights belong to HighWay Company
 * User: Yuriy
 * Date: 27.10.13
 * Time: 16:31
 */
public class ExcModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(ExcService.class).in(Scopes.SINGLETON);
    }
}
