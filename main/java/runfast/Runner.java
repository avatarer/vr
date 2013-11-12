package runfast;

import com.google.inject.Inject;
import com.google.inject.Injector;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.slf4j.Logger;
import web.WebServiceException;
import web.WebUtils;
import web.anew.model.translators.CarPartFromExcel;
import web.anew.model.translators.FlatRentFromExcel;
import web.anew.post.AbstractPost;
import web.anew.post.CarPartPost;
import web.anew.post.FlatRentPost;
import web.anew.toolkit.AvitoPageTools;
import web.driver.DriverJsService;
import web.driver.DriverService;
import web.exc.ExcList;
import web.exc.ExcService;
import web.exc.ExcTranslator;
import web.logger.InjectLogger;
import web.tools.JsTools;

import java.io.File;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * All rights belong to HighWay Company
 * User: Yuriy
 * Date: 28.10.13
 * Time: 7:51
 */
public class Runner {
    public static final String PREFIX = "runfast.";
    public static final String PHOTO_DIR = PREFIX + "PHOTO_DIR";
    public static final String EXCEL_PATH = PREFIX + "EXCEL_PATH";
    public static final String EXCEL_CURRENT_POSITION = PREFIX + "EXCEL_CURRENT_POSITION";
    public static final String EXCEL_COUNT_POSITION = PREFIX + "EXCEL_COUNT_POSITION";
    public static final String AVITO_LOGIN = PREFIX + "AVITO_LOGIN";
    public static final String AVITO_PASSWORD = PREFIX + "AVITO_PASSWORD";
    public static final String POST_FAMILY = PREFIX + "POST_FAMILY";

    @InjectLogger
    private Logger logger;

    @Inject
    private Injector injector;

    @Inject
    private DriverService driverService;

    @Inject
    private JsTools jsTools;

    @Inject
    private DriverJsService jsService;

    @Inject
    private AvitoPageTools pageTools;

    @Inject
    private ExcService excService;

    private ExcList list;

    private AbstractPost post;

    private File confFile = new File("config.properties");


    private PropertiesConfiguration config;

    private void loadConfig() throws ConfigurationException {
        this.config = new PropertiesConfiguration(confFile);
    }

    private void prepareForPost() throws WebServiceException {
        driverService.start();
        jsService.inject(jsTools.createJsLoader("commons"));
        jsService.inject(jsTools.createJsLoader("avitoTools"));
        jsService.inject(jsTools.createJsLoader("levDist"));
        pageTools.login(config.getString(AVITO_LOGIN), config.getString(AVITO_PASSWORD));
        // HardDialog.approve("Вход выполнен ?");
    }

    private void loadData() throws IOException {
        ExcTranslator translator;
        File excelFile = new File(config.getString(PHOTO_DIR));
        switch (config.getString(POST_FAMILY)) {
            case "flat":
                translator = new FlatRentFromExcel(excelFile);
                post = injector.getInstance(FlatRentPost.class);
                break;
            case "part":
                translator = new CarPartFromExcel(excelFile);
                post = injector.getInstance(CarPartPost.class);
                break;
            default:
                logger.error("post family is not defined");
                throw new RuntimeException("post family is not defined");
        }
        injector.injectMembers(translator);
        list = excService.createList(new File(config.getString(EXCEL_PATH)), translator);
        post.configure(config);
    }


    public void run() throws Exception {
        System.setProperty("org.slf4j.simpleLogger.logFile", "log.log");
        try {
            loadConfig();
            loadData();
            prepareForPost();
            int position = config.getInt(EXCEL_CURRENT_POSITION);
            int end = config.getInt(EXCEL_COUNT_POSITION) + position;
            while (position <= end) {
                Object model = list.get(position);
                post.post(model);
                position++;
                config.setProperty(EXCEL_CURRENT_POSITION, position);
                config.save(confFile);
            }
        } catch (Exception e) {
            logger.error("exception", e);
            e.printStackTrace();
            HardDialog.exit("Ошибка, Скинь лог файл");
            throw e;
        }
    }

    public static void main(String args[]) throws Exception {
        Injector inj = WebUtils.createInjector();
        inj.getInstance(Runner.class).run();
    }
}
