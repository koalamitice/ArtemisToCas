package a2c.Util;


import org.apache.commons.csv.CSVFormat;

public class ArtemisFormat {

    public static CSVFormat getArtemisFormat() {
        CSVFormat format = CSVFormat.DEFAULT.withDelimiter(';');
        return format;
    }

    /**
     * might change each semester, please update
     * LAST UPDATE: SS23
     */
    public static final int INDEX_TOTALPOINTS = 44;
    public static final int INDEX_MAIL = 2;

}
