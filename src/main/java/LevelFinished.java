package src.main.java;
import java.io.*;
import java.util.HashSet;
import java.util.Set;

/**
 * This class is responsible for managing completed levels in a game. It provides functionality to add new levels as
 * they are completed by the player and ensures that these entries are unique to maintain an accurate record of progress.
 * It also adds the next sequential level to encourage continued play.
 *
 * @author zaid.sattar
 */
public class LevelFinished {
    private static final String FILE_PATH = "completedLevels.csv"; // Path to the CSV file storing completed levels.

    /**
     * Constructs a new LevelFinished instance and writes the specified level and its next sequential level to
     * completedLevels.csv, ensuring there are no duplicates.
     *
     * @param level The level that has just been completed.
     */
    public LevelFinished(String level) {
        writeLevelsToFile(level);
    }

    /**
     * Writes the specified level and its next sequential level to the completedLevels.csv file. This method ensures
     * that each entry is unique, preventing duplicate records of completed levels.
     *
     * @param currentLevel The level that has just been completed.
     */
    private void writeLevelsToFile(String currentLevel) {
        String levelNumberPart = currentLevel.replaceAll("[^0-9]", "");
        int levelNumber = Integer.parseInt(levelNumberPart);
        String nextLevel = "Level" + (levelNumber + 1);

        Set<String> existingLevels = loadExistingLevels();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            if (!existingLevels.contains(currentLevel)) {
                writer.write(currentLevel);
                writer.newLine();
            }
            if (!existingLevels.contains(nextLevel)) {
                writer.write(nextLevel);
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error writing to completedLevels.csv: " + e.getMessage());
        }
    }

    /**
     * Loads and returns a set of levels that have already been completed as recorded in completedLevels.csv.
     * This method is used to ensure that the file is only appended with new or next sequential levels that have not
     * been previously recorded.
     *
     * @return A Set of Strings representing the levels that have already been completed.
     */
    private Set<String> loadExistingLevels() {
        Set<String> levels = new HashSet<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                levels.add(line.trim());
            }
        } catch (IOException e) {
            System.err.println("Error reading from completedLevels.csv: " + e.getMessage());
        }
        return levels;
    }

    /**
     * The main method to demonstrate the functionality of adding a completed level and its subsequent level to
     * the completedLevels.csv file.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        new LevelFinished("Level1");
    }
}
