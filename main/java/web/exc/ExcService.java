package web.exc;

import com.google.inject.ImplementedBy;

import java.io.File;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * All rights belong to HighWay Company
 * User: Yuriy
 * Date: 27.10.13
 * Time: 15:50
 */
@ImplementedBy(ExcelExcService.class)
public interface ExcService {

    <T> ExcList<T> createList(File source, int sheet, int meta, ExcTranslator<T> translator) throws IOException;

    <T> ExcList<T> createList(File source, int sheet, ExcTranslator<T> translator) throws IOException;

    <T> ExcList<T> createList(File source, ExcTranslator<T> translator) throws IOException;
}
