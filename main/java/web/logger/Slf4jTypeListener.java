package web.logger;

import com.google.inject.TypeLiteral;
import com.google.inject.spi.TypeEncounter;
import com.google.inject.spi.TypeListener;
import org.slf4j.Logger;

import java.lang.reflect.Field;

/**
 * Created with IntelliJ IDEA.
 * User: IceX
 * Date: 29.10.13
 * Time: 14:07
 * To change this template use File | Settings | File Templates.
 */
public class Slf4jTypeListener implements TypeListener {
    public <T> void hear(TypeLiteral<T> typeLiteral, TypeEncounter<T> typeEncounter) {
        for (Field field : typeLiteral.getRawType().getDeclaredFields()) {
            if (field.getType() == Logger.class
                    && field.isAnnotationPresent(InjectLogger.class)) {
                typeEncounter.register(new Slf4JMembersInjector<T>(field));
            }
        }
    }
}