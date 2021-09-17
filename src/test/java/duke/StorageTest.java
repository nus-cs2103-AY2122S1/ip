package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import tasks.Event;
import tasks.ToDo;

/**
 * JUnit test class for Storage.
 */
public class StorageTest {
    private Storage storage;
    private String filePath;

    @BeforeEach
    public void setUp() {
        this.filePath = "../../storage/testSave.txt";
        this.storage = new Storage(this.filePath);
    }

    /**
     * Tests if an empty .txt file is created by the retrieve() method.
     */
    @Test
    public void creationTest() {
        try {
            storage.retrieve();
        } catch (IOException e) {
            System.out.println("Creation error: " + e.getMessage());
        }
        assertTrue(new File(this.filePath).exists());
    }

    /**
     * Tests if the save() method correctly saves a TaskList.
     */
    @Test
    public void saveTest() {
        TaskList taskList = new TaskList();
        taskList.add(new ToDo("take your medicine"));
        taskList.add(new Event("family dinner", "2021-08-25"));
        try {
            storage.save(taskList);
            FileReader fr = new FileReader(this.filePath);
            BufferedReader br = new BufferedReader(fr);
            String line = br.lines().collect(Collectors.joining("\n")) + "\n";
            assertEquals(taskList.toStorage(), line);
        } catch (Exception e) {
            System.out.println("Save error: " + e.getMessage());
        }
    }

    /**
     * Tests if the retrieve() method is able to properly retrieve the contents of the .txt file.
     */
    @Test
    public void retrievalTest() {
        String actualString = "a";
        String expectedString = "b";
        try {
            FileReader actualFile = new FileReader(storage.retrieve());
            BufferedReader actualBr = new BufferedReader(actualFile);
            actualString = actualBr.lines().collect(Collectors.joining());

            FileReader expectedFile = new FileReader(this.filePath);
            BufferedReader expectedBr = new BufferedReader(expectedFile);
            expectedString = expectedBr.lines().collect(Collectors.joining());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        assertEquals(expectedString, actualString);
    }
}
