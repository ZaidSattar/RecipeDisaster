package src.main.java;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Utility class for reading data from a CSV file into a double list.
 */
public class CSVDoubleListReader {
    /**
     * Reads a CSV file and returns its content as a double list of strings.
     *
     * @param filename the path to the CSV file to be read
     * @return a List of Lists containing the data read from the CSV file
     */
    public static List<List<String>> readCSV(String filename) {
        List<List<String>> doubleList = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                List<String> row = new ArrayList<>();
                for (String item : data) {
                    row.add(item);
                }
                doubleList.add(row);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return doubleList;
    }
}


