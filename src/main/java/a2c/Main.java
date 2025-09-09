package a2c;

import a2c.Util.CSVUtil;
import a2c.model.Student;

import java.io.File;

public class Main {
    public static void main(String[] args) {

        /**
         * HOW TO USE THIS CODE:
         * (1) set correct values for the Strings below
         * (2) Check if CASFormat Header still fits the CAS standard
         * (3) Update indices in ArtemisFormat (needed to find column with mail and points)
         * ... the indices change depending on the number of tasks that were handed out during the semester
         * (4) Start Program :)
         */
        final String ARTEMIS_FILE_PATH = "/Users/koalamitice/Desktop/aaa/ArtemisExport.csv";
        final String CAS_FILE_PATH = "/Users/koalamitice/Desktop/aaa/CampusExport.csv";
        final String OUTPUT_FILE_PATH = "/Users/koalamitice/Desktop/aaa/output.csv";

        /*
         * Make sure to delete this line from the CAS Export once you copied it here. Use Texteditor to delete the whole line.
         * Also make sure to copy the weird "Ü" like it is in the example.
         */
        final String CAS_PREABMLE = "7500250, Softwaretechnik I ‹bungsschein, Campus-ID: 0x387F8E55A6D74B44BEEF7F9BE6CF7297";

        //points that are needed to pass the course:
        Student.needed_to_pass = 63.0;

        new CSVUtil().startConvertion(
                new File(ARTEMIS_FILE_PATH),
                new File(CAS_FILE_PATH),
                OUTPUT_FILE_PATH,
                CAS_PREABMLE);
    }
}