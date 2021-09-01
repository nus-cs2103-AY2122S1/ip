package petal.components;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import petal.Petal;
import stubs.TaskListStub;

public class StorageTest {

    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private final Petal petal = new Petal();
    private final TaskListStub taskListStub = new TaskListStub();
    private final Storage storage = new Storage(taskListStub);
    private final String folderPath = System.getProperty("user.dir") + "/PetalData";
    private final String filePath = folderPath + "/Tasks.txt";

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStream));
    }

    @Test
    public void createDirectory_noInput_folderAndFileExists() {
        assertEquals(Responses.WELCOME_BACK.toString(), storage.createDirectory());
    }

    @Test
    public void testIfPetalUsedBefore_noInput_truePetalUsedBefore() {
        assertTrue(storage.hasUsedPetalBefore());
    }

    @Test
    public void saveTasks_noInput_nothingDisplayed() throws IOException {
        storage.saveTasks();
        assertEquals("", outputStream.toString().trim());
    }


}
