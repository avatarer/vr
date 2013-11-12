package web.validator;

import org.hibernate.validator.HibernateValidator;

import javax.inject.Provider;
import javax.validation.Validation;
import javax.validation.Validator;

/**
 * Created with IntelliJ IDEA.
 * User: IceX
 * Date: 30.10.13
 * Time: 16:01
 * To change this template use File | Settings | File Templates.
 */
class ValidatorProvider implements Provider<Validator> {
    @Override
    public Validator get() {
        return Validation.buildDefaultValidatorFactory().getValidator();
    }
}
