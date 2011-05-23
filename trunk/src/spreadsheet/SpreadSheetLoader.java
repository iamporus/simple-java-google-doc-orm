package spreadsheet;

import java.util.List;

/**
 * A type to load data from a spreadsheet.
 */
public interface SpreadSheetLoader {
    
    void setWorkbook(String workbook);
    
    <T> List<T> loadSheetAsList(String spreadSheet, Class<T> clazz);
    
}
