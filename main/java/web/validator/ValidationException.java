package web.validator;

import javax.validation.ConstraintViolation;
import java.util.List;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: IceX
 * Date: 30.10.13
 * Time: 16:18
 * To change this template use File | Settings | File Templates.
 */
public class ValidationException extends RuntimeException {
    private final Set<ConstraintViolation<Object>> violations;

    public ValidationException(Set<ConstraintViolation<Object>> violations) {

        this.violations = violations;
    }

    @Override
    public String getMessage() {
        return violations.toString();
    }
}
