package web.validator;

import com.google.inject.ImplementedBy;

import javax.inject.Singleton;

/**
 * Created with IntelliJ IDEA.
 * User: IceX
 * Date: 30.10.13
 * Time: 16:17
 * To change this template use File | Settings | File Templates.
 */
@ImplementedBy(ValidationServiceImpl.class)
public interface ValidationService {
    void validate(Object o);
}
