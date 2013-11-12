package web.anew.post;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: IceX
 * Date: 30.10.13
 * Time: 14:54
 * To change this template use File | Settings | File Templates.
 */
public class PostStateException extends Exception {
    private final List<String> errors;

    public PostStateException(List<String> errors) {
        this.errors = errors;
    }

    @Override
    public String getMessage() {
        if (errors == null) {
            return "";
        }
        return errors.toString();
    }
}
