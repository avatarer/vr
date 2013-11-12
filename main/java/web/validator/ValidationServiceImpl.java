package web.validator;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: IceX
 * Date: 30.10.13
 * Time: 16:17
 * To change this template use File | Settings | File Templates.
 */
@Singleton
class ValidationServiceImpl implements ValidationService {

    @Inject
    private Validator validator;

    @Override
    public void validate(Object o) {
        Set<ConstraintViolation<Object>> validate = validator.validate(o);
        if (validate.size() > 0) {
            throw new ValidationException(validate);
        }
    }
}
