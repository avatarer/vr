package web.logger;

import java.lang.annotation.*;

/**
 * Created with IntelliJ IDEA.
 * User: IceX
 * Date: 29.10.13
 * Time: 14:08
 * To change this template use File | Settings | File Templates.
 */
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Target(value = ElementType.FIELD)
public @interface InjectLogger {
}
