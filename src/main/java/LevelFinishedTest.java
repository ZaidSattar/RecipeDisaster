package src.main.java;
import org.junit.jupiter.api.*;
import java.io.*;
import java.nio.file.*;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class LevelFinishedTest {

    private static final String FILE_PATH = "./resources/completedLevels.csv";
    private static final Path path = Paths.get(FILE_PATH);
    private static final Path backupPath = Paths.get(FILE_PATH + ".backup"); // Backup file path

    @BeforeAll
    void setup() throws IOException {
        // Ensures a clean state before all tests by backing up the file
        if (Files.exists(path)) {
            Files.copy(path, backupPath, StandardCopyOption.REPLACE_EXISTING);
        }
        Files.deleteIfExists(path); // Ensures tests start with a clean state
    }

    @AfterEach
    void cleanUp() throws IOException {
        Files.deleteIfExists(path);
    }

    @AfterAll
    void tearDown() throws IOException {
        // Restore the original file from the backup after all tests
        if (Files.exists(backupPath)) {
            Files.move(backupPath, path, StandardCopyOption.REPLACE_EXISTING);
        }
    }

    @Test
    public void testAddingNewLevel() throws IOException {
        new LevelFinished("Level1");
        assertTrue(Files.exists(path));
        List<String> lines = Files.readAllLines(path);
        assertTrue(lines.contains("Level1"));
        assertTrue(lines.contains("Level2"));
    }

    @Test
    public void testAddingExistingLevelDoesNotDuplicate() throws IOException {
        Files.write(path, Arrays.asList("Level1", "Level2"));

        // Try to add Level1 again
        new LevelFinished("Level1");

        List<String> lines = Files.readAllLines(path);
        assertEquals(2, lines.size()); //  no duplicate entries
    }

    @Test
    public void testAddingSequentialLevels() throws IOException {
        // Add Level1, which should also add Level2
        new LevelFinished("Level1");

        // Add Level2 again, which should add Level3 without duplicating Level2
        new LevelFinished("Level2");

        List<String> lines = Files.readAllLines(path);
        assertTrue(lines.contains("Level1"));
        assertTrue(lines.contains("Level2"));
        assertTrue(lines.contains("Level3"));
        assertEquals(3, lines.size()); // Ensure correct number of entries
    }
}
