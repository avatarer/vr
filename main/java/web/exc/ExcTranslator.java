package web.exc;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * All rights belong to HighWay Company
 * User: Yuriy
 * Date: 27.10.13
 * Time: 15:51
 */
public interface ExcTranslator<T> {
    void initMeta(List<String> data);

    T translate(List<String> cells);
}
