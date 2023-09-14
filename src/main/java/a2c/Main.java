package a2c;

import a2c.Util.CSVUtil;
import java.io.File;

public class Main {
    public static void main(String[] args) {

        /**
         * HOW TO USE THIS CODE:
         * (1) set correct values for the Strings below
         * (2) Check if CASFormat Header still fits the CAS standard
         * (3) Updated indices in ArtemisFormat (needed to find column with mail and points)
         * ... the indices change depending on the number of tasks that were handed out during the semester
         * (4) Start Program :)
         */
        final String ARTEMIS_FILE_PATH = "/path/to/file/exported/from/artemis";
        final String CAS_FILE_PATH = "/path/to/file/exported/from/cas";
        final String OUTPUT_FILE_PATH = "/path/where/output/should/be/placed";

        final String CAS_PREABMLE = "COPY FIRST LINE FROM CAS EXPORT (changes every year, identifies exam)";

        new CSVUtil().startConvertion(
                new File(ARTEMIS_FILE_PATH),
                new File(CAS_FILE_PATH),
                OUTPUT_FILE_PATH,
                CAS_PREABMLE);
    }
}