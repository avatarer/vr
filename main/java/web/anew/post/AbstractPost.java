package web.anew.post;

import com.antigate.exception.AntigateException;
import com.google.inject.Inject;
import org.apache.commons.configuration.Configuration;
import org.slf4j.Logger;
import web.anew.data.ConfigurationNames;
import web.anew.data.Preferences;
import web.anew.toolkit.AvitoPageTools;
import web.anew.toolkit.AvitoPostTools;
import web.driver.DriverJsService;
import web.logger.InjectLogger;
import web.tools.PageTools;
import web.validator.ValidationService;

import javax.validation.constraints.Min;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: IceX
 * Date: 30.10.13
 * Time: 14:49
 * To change this template use File | Settings | File Templates.
 */

public abstract class AbstractPost<T> {

    @Min(1)
    private int postTries = Preferences.postDefaultTryNumber;

    @Min(1)
    private int photoTries = Preferences.postPhotoDefaultTryNumber;

    @Min(1)
    private int captchaTries = Preferences.postDefaultCaptchaTries;

    @Inject
    private AvitoPageTools avitoPageTools;

    @Inject
    private AvitoPostTools avitoPostTools;

    @Inject
    protected PageTools pageTools;

    @InjectLogger
    protected Logger logger;

    @Inject
    private ValidationService validationService;

    abstract protected void setHeaders(T model);

    abstract protected List<String> getPhotos(T model);

    public void configure(Configuration config) {
        postTries = config.getInt(ConfigurationNames.POST_TRIES, this.postTries);
        photoTries = config.getInt(ConfigurationNames.PHOTO_TRIES, this.photoTries);
        captchaTries = config.getInt(ConfigurationNames.CAPTCHA_TRIES, this.captchaTries);
        validationService.validate(this);
    }

    private void postPhotos(List<String> photos) {
        if ((photos == null) || (photos.size() == 0)) {
            return;
        }
        avitoPostTools.postPhoto(photoTries, photos);
    }

    public void post(T model) throws PostStateException {
        Exception ex = null;
        int ptc = 0;
        while (ptc <= postTries) {
            try {
                avitoPageTools.gotoItemAdd();
                setHeaders(model);
                postPhotos(getPhotos(model));
                avitoPostTools.finish1AdvertForm();
                List<String> errors = avitoPostTools.getErrors();
                if (errors.size() != 0) {
                    throw new PostStateException(errors);
                }
                int ctc = 1;
                List<String> cerrors = null;
                while (ctc <= captchaTries) {
                    try {
                        avitoPostTools.doAdvert2Form();
                    } catch (AntigateException e) {
                        logger.error("captcha", e);
                        switch (e.getErrorCode()) {
                            case IO_EXCEPTION:
                                continue;
                            default:
                                throw new RuntimeException(e);
                        }
                    }
                    cerrors = avitoPostTools.getErrors();
                    if (cerrors.size() == 0) {
                        break;
                    }
                }
                if (cerrors.size() != 0) {
                    throw new PostStateException(cerrors);
                }
                return;
            } catch (Exception e) {
                logger.error("post", e);
                if (ptc == postTries) {
                    throw e;
                }
            } finally {
                ptc++;
            }
        }
    }

    protected void setHeader(String header, String value) {
        avitoPostTools.setHeader(header, value);
    }
}
