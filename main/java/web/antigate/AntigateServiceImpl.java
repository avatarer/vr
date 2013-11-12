package web.antigate;

import com.antigate.AntigateFacade;
import com.antigate.exception.AntigateException;
import com.antigate.exception.ErrorCode;
import com.antigate.responses.GetCaptchaStatusResponse;
import com.antigate.responses.SendFileResponse;
import com.google.inject.Inject;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.BASE64Decoder;

import java.io.File;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: IceX
 * Date: 29.10.13
 * Time: 13:34
 * To change this template use File | Settings | File Templates.
 */
public class AntigateServiceImpl implements AntigateService {
    @Inject
    private BASE64Decoder decoder;
    @Inject
    private AntigateFacade facade;

    private Logger logger = LoggerFactory.getLogger(AntigateServiceImpl.class);

    @Override
    public String fileRequest(File file) throws AntigateException {
        try {
            SendFileResponse response = facade.sendFile(file.getAbsolutePath());
            String captcha = null;
            loop:
            for (int i = 0; i < 24; i++) {
                GetCaptchaStatusResponse status = facade.getCaptchaStatus(response.getCaptchaID());
                switch (status.getCaptchaStatus()) {
                    case OK:
                        captcha = status.getCaptchaWord();
                        break loop;
                    default:
                        try {
                            Thread.sleep(5000);
                        } catch (Exception e) {
                            logger.error("interrupted ???", e);
                            System.exit(1);
                        }
                        break;
                }
            }
            return captcha;
        } catch (IOException e) {
            throw new AntigateException(ErrorCode.IO_EXCEPTION);
        }
    }

    @Override
    public String base64Request(String base64) throws AntigateException {
        try {
            File tmpImg = File.createTempFile(String.valueOf(Math.random()), "-captcha.jpg");
            FileUtils.writeByteArrayToFile(tmpImg, decoder.decodeBuffer(base64));
            tmpImg.deleteOnExit();
            return fileRequest(tmpImg);
        } catch (IOException e) {
            throw new AntigateException(ErrorCode.IO_EXCEPTION);
        }
    }
}
