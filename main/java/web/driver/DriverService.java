package web.driver;

import com.google.inject.ImplementedBy;
import org.apache.commons.configuration.Configuration;
import web.WebServiceException;
import web.driver.impl.DriverServiceImpl;

/**
 * Created with IntelliJ IDEA.
 * All rights belong to HighWay Company
 * User: Yuriy
 * Date: 27.10.13
 * Time: 12:03
 */
@ImplementedBy(DriverServiceImpl.class)
public interface DriverService {

    void appendConfiguration(Configuration config);

    void start() throws WebServiceException;

    void stop();

    void clear() throws WebServiceException;
}
