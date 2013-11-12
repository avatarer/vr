package web.exc;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * All rights belong to HighWay Company
 * User: Yuriy
 * Date: 27.10.13
 * Time: 15:58
 */
public class ExcelExcList<T> implements ExcList<T> {
    final Sheet sheet;
    final ExcTranslator<T> translator;

    public ExcelExcList(Sheet sheet, ExcTranslator<T> translator) {
        this.sheet = sheet;
        this.translator = translator;
    }

    @Override
    public int size() {
        return sheet.getLastRowNum() + 1;
    }

    private List<String> readCells(int row) {
        if (row > sheet.getLastRowNum()) {
            throw new IllegalArgumentException("Row is greater then numrows");
        }
        List<String> list = new ArrayList<>();
        Row cells = sheet.getRow(row);
        for (Cell cell : cells) {
            String cellValue;
            switch (cell.getCellType()) {
                case Cell.CELL_TYPE_NUMERIC:
                    double value = cell.getNumericCellValue();
                    if (value - Math.floor(value) == 0) {
                        long iVal = Math.round(value);
                        cellValue = String.valueOf(iVal);
                    } else {
                        cellValue = String.valueOf(value);
                    }
                    break;
                default:
                    cellValue = cell.toString();
            }
            list.add(cellValue);
        }
        return list;
    }

    public void initMeta(int index) {
        translator.initMeta(readCells(index));
    }

    @Override
    public T get(int index) {
        return translator.translate(readCells(index));
    }

    @Override
    public Iterator<T> iterator() {
        return new ExcIteratorImpl<T>(this);
    }
}
