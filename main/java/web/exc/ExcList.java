package web.exc;

/**
 * Created with IntelliJ IDEA.
 * All rights belong to HighWay Company
 * User: Yuriy
 * Date: 27.10.13
 * Time: 15:52
 */
public interface ExcList<T> extends Iterable<T> {
    int size();

    /**
     * 0 based
     * @param index
     * @return
     */
    T get(int index);
}
