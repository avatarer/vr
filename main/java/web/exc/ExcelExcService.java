package web.exc;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * All rights belong to HighWay Company
 * User: Yuriy
 * Date: 27.10.13
 * Time: 16:09
 */
public class ExcelExcService implements ExcService {

    private Logger logger = LoggerFactory.getLogger(ExcelExcService.class);

    private Workbook loadWorkbook(File file) throws IOException {
        Workbook workbook = null;
        String ext = file.getName().substring(file.getName().lastIndexOf('.'));
        switch (ext) {
            case ".xls":
                workbook = new HSSFWorkbook(FileUtils.openInputStream(file));
                break;
            case ".xlsx":
                workbook = new HSSFWorkbook(FileUtils.openInputStream(file));
                break;
            default:
                throw new IllegalArgumentException("File is not excel workbook\n" + file);
        }
        return workbook;
    }

    @Override
    public <T> ExcList<T> createList(File source, int sheet, int meta, ExcTranslator<T> translator) throws IOException {
        Workbook workbook = null;
        Sheet mSheet = null;
        ExcelExcList<T> excList = null;
        try {
            workbook = loadWorkbook(source);
            mSheet = workbook.getSheetAt(sheet);
            if (mSheet == null) {
                throw new IllegalArgumentException("sheet is not defined");
            }
            excList = new ExcelExcList<>(mSheet, translator);
            if ((meta > 0) && (meta < excList.size())) {
                excList.initMeta(meta);
            } else if ((meta > 0) && (meta >= excList.size())) {
                throw new IllegalArgumentException("Meta is too high");
            }
        } catch (Exception e) {
            logger.error("service exception", e);
            throw e;
        }
        return excList;
    }

    @Override
    public <T> ExcList<T> createList(File source, int sheet, ExcTranslator<T> translator) throws IOException {
        return createList(source, sheet, -1, translator);
    }

    @Override
    public <T> ExcList<T> createList(File source, ExcTranslator<T> translator) throws IOException {
        return createList(source, 0, -1, translator);
    }
}
