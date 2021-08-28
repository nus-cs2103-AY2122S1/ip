package petal.components;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import petal.Petal;
import stubs.TaskListStub;

public class StorageTest {

    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private final Petal petal = new Petal();
    private final Ui ui = new Ui(petal);
    private final TaskListStub taskListStub = new TaskListStub(ui);
    private final Storage storage = new Storage(taskListStub, ui);
    private final String folderPath = System.getProperty("user.dir") + "/PetalData";
    private final String filePath = folderPath + "/Tasks.txt";

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStream));
    }

    @Test
    public void createDirectory_noInput_folderAndFileExists() {
        storage.createDirectory();
        Path folder = Paths.get(folderPath);
        Path file = Paths.get(filePath);
        assertEquals(true, Files.exists(folder));
        assertEquals(true, Files.exists(file));
    }

    @Test
    public void retrieveTasks_noInput_returnFalse() {
        boolean tasksAvailable = storage.retrieveTasks();
        assertEquals(true, tasksAvailable);
    }

    @Test
    public void saveTasks_noInput_nothingDisplayed() throws IOException {
        storage.saveTasks();
        assertEquals("", outputStream.toString().trim());
    }


}
