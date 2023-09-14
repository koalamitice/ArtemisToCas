package a2c.Util;

import org.apache.commons.csv.CSVFormat;

public class CASFormat {

    /**
     * CAS header might change over time...
     * last updated: SS23
     */
    public static final String HEADER_ID_MAIL = "E-Mail (Hochschule)";
    public static final String HEADER_ID_PUNKTE = "Punkte";
    public static final String HEADER_ID_NOTE = "Note";
    private static final String[] HEADER = {
            "Matrikelnummer",
            "Aktueller Status",
            "Name",
            "Vorname",
            HEADER_ID_MAIL,
            "Studiengang",
            "Titel",
            "Prüfungsnummer",
            "Fachsemester",
            "Anmeldedatum",
            HEADER_ID_NOTE,
            HEADER_ID_PUNKTE,
            "Prüfungsdatum",
            "Versuch",
            "Teilleistung"
    };

    public static CSVFormat getCASFormat() {
        CSVFormat format = CSVFormat.DEFAULT.withDelimiter(';');
        format = format.withHeader(HEADER);
        return format;
    }

    public static String[] getCASHeader() {
        return HEADER;
    }

}
