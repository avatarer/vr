package web.antigate;

import com.antigate.AntigateFacade;
import com.antigate.config.AntigateConfig;
import com.antigate.config.DefaultAntigateConfig;
import com.google.inject.AbstractModule;

/**
 * Created with IntelliJ IDEA.
 * All rights belong to HighWay Company
 * User: Yuriy
 * Date: 28.10.13
 * Time: 9:39
 */
public class AntigateModule extends AbstractModule {

    @Override
    protected void configure() {
        AntigateConfig config = new DefaultAntigateConfig();
        config.setKey("3f84ec88d2bd85438e9be0e3a9ca4569");
        AntigateFacade facade = new AntigateFacade(config);
        bind(AntigateFacade.class).toInstance(facade);
        bind(AntigateService.class);
    }
}
