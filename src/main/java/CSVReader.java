package src.main.java;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Utility class for reading data from a CSV file.
 */
public class CSVReader {
    /**
     * Reads a CSV file and returns its content as an ArrayList of strings.
     *
     * @param filename the path to the CSV file to be read
     * @return an ArrayList containing the data read from the CSV file
     */
    public static ArrayList<String> readCSV(String filename) {
        ArrayList<String> dataList = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            boolean isFirstLine = true;

            while ((line = br.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue; // Skip the first line (header)
                }
                dataList.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return dataList;
    }
}

