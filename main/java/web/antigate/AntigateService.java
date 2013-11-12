package web.antigate;

import com.antigate.exception.AntigateException;
import com.google.inject.ImplementedBy;

import java.io.File;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: IceX
 * Date: 29.10.13
 * Time: 13:33
 * To change this template use File | Settings | File Templates.
 */

@ImplementedBy(AntigateServiceImpl.class)
public interface AntigateService {
    String fileRequest(File file) throws AntigateException;

    String base64Request(String base64) throws AntigateException;
}
