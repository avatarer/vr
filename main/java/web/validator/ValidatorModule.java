package web.validator;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;

import javax.validation.Validator;

/**
 * Created with IntelliJ IDEA.
 * User: IceX
 * Date: 30.10.13
 * Time: 15:59
 * To change this template use File | Settings | File Templates.
 */
public class ValidatorModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(Validator.class).toProvider(ValidatorProvider.class).in(Scopes.SINGLETON);
        bind(ValidationService.class);
    }
}
