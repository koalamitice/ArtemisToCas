package a2c.Util;

import a2c.model.Student;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class CSVUtil {

    public void startConvertion (File artemisExport, File casImport, String outputPath, String CASpreamble) {
        //Merging Process:
        List<Student> studentList = null;
        //Read CSV File Exported from Artemis:
        try {
            studentList = parseCASImport(casImport);
            parseArtemisExport(artemisExport, studentList);
        } catch (IOException e) {
            System.err.println("Error While Parsing File");
            e.printStackTrace();
        }

        if (studentList.isEmpty()) {
            System.err.println("EMPTY STUDENT LIST, ABORTING MISSION!!!");
            System.exit(1);
        }

        //create a new CSV file to write the result
        try {
            writeResultCSVFile(outputPath, CASpreamble, studentList);
        } catch (IOException e) {
            System.err.println("Error While Writing in CAS File");
            e.printStackTrace();
        }
    }

    private static List<Student> parseCASImport(File file) throws IOException {
        List<Student> students = new ArrayList<Student>();
        CSVFormat format = CASFormat.getCASFormat();

        Reader inputReader = new FileReader(file);
        Iterable<CSVRecord> records = format.parse(inputReader);

        for (CSVRecord record : records) {
            String[] data = new String[CASFormat.getCASHeader().length];
            if (record.get(0).equals(CASFormat.getCASHeader()[0])) continue;

            int counter = 0;
            for (String value : CASFormat.getCASHeader()) {
                data[counter] = record.get(value);
                counter++;
            }
            Student student = new Student(data);
            students.add(student);
        }

        inputReader.close();

        return students;
    }

    /**
     * Parses Artemis Export file and adds missing data to Students
     */
    private static void parseArtemisExport(File file, List<Student> studentList) throws IOException {
        CSVFormat format = ArtemisFormat.getArtemisFormat();
        Reader inputReader = new FileReader(file);
        Iterable<CSVRecord> records = format.parse(inputReader);

        for (CSVRecord record : records) {
            String recordMail = record.get(ArtemisFormat.INDEX_MAIL);
            String totalPoints = record.get(ArtemisFormat.INDEX_TOTALPOINTS);

            boolean foundStudent = false;

            for (Student student : studentList) {
                String studentMail = student.getDataMap().get(CASFormat.HEADER_ID_MAIL);
                if (!studentMail.equals(recordMail)) continue;

                student.setTotalPoints(totalPoints);
                //add points:
                student.getDataMap().put(CASFormat.HEADER_ID_PUNKTE, totalPoints);
                student.getDataMap().put(CASFormat.HEADER_ID_NOTE, student.getGrade());
                foundStudent = true;
                break;
            }

            if (!foundStudent) {
                System.err.println("Unregistered Student found: " + recordMail);
            }
        }

        inputReader.close();
    }

    /**
     * writes a new CSV files that can be imported to CAS
     * @param outputPath - in which the file should be written
     * @param students - list of students
     * @throws IOException
     */
    private static void writeResultCSVFile (String outputPath, String preamble, List<Student> students) throws IOException {
        if (outputPath == null || outputPath.isEmpty() || students == null) return;

        BufferedWriter writer = Files.newBufferedWriter(Paths.get(outputPath));
        writer.write(preamble + "\n");

        CSVPrinter csvPrinter = new CSVPrinter(writer,
                CSVFormat.DEFAULT
                        .withHeader(CASFormat.getCASHeader())
                        .withDelimiter(';'));

        for (Student student : students) {
            csvPrinter.printRecord(student.getDataMap().values());
        }

        writer.close();
    }
}
