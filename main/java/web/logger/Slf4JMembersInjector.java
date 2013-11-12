package web.logger;

import com.google.inject.MembersInjector;
import com.google.inject.spi.InjectionListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;

/**
 * Created with IntelliJ IDEA.
 * User: IceX
 * Date: 29.10.13
 * Time: 14:10
 * To change this template use File | Settings | File Templates.
 */
public class Slf4JMembersInjector<T> implements MembersInjector<T> {
    private final Field field;
    private final Logger logger;

    Slf4JMembersInjector(Field field) {
        this.field = field;
        this.logger = LoggerFactory.getLogger(field.getDeclaringClass());
        field.setAccessible(true);
    }

    public void injectMembers(T t) {
        try {
            field.set(t, logger);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

}
