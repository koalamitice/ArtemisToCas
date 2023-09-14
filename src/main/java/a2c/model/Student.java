package a2c.model;

import a2c.Util.CASFormat;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class Student {

    public Student(String[] data) {
        String[] HEADER = CASFormat.getCASHeader();
        if (data.length != HEADER.length) {
            System.err.println("Error While Creating Student:" + data[0]); //should print MatNr.
            System.exit(0);
        }
        dataMap = new LinkedHashMap<String, String>();
        for (int i = 0; i < HEADER.length; i++) {
            dataMap.put(HEADER[i], data[i]);
        }
    }

    private Map<String, String> dataMap;
    public Map<String, String> getDataMap() {
        return dataMap;
    }

    private String totalPoints;

    public void setTotalPoints(String points) {
        totalPoints = points;
    }

    public String getGrade() {
        if (Double.parseDouble(totalPoints.replaceAll(",", "."))
                >= 63.0) return "bestanden";
        return "nicht bestanden";
    }

    public void printData() {
        for (String value : dataMap.values()) {
            System.out.print("\"" + value + "\"" + ";");
        }
        System.out.print("\n");
    }
}
