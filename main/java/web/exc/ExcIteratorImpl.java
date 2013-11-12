package web.exc;

import java.util.Iterator;

/**
 * Created with IntelliJ IDEA.
 * All rights belong to HighWay Company
 * User: Yuriy
 * Date: 27.10.13
 * Time: 17:16
 */
public class ExcIteratorImpl<T> implements Iterator<T> {
    private final ExcList<T> excList;
    private int position = 0;

    public ExcIteratorImpl(ExcList<T> excList) {
        this.excList = excList;
    }

    @Override
    public boolean hasNext() {
        return position < excList.size();
    }

    @Override
    public T next() {
        position++;
        if (position >= excList.size()) {
            return null;
        }
        return excList.get(position);
    }

    public void reset() {
        position = 0;
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }
}
