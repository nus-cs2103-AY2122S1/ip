package seedu.duke;

import org.junit.Rule;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.rules.TemporaryFolder;
import seedu.duke.task.Task;
import seedu.duke.task.TaskList;
import seedu.duke.task.ToDo;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

public class StorageTest {

    @Rule
    public TemporaryFolder folder= new TemporaryFolder();

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream initialOut = System.out;
    private final PrintStream initialErr = System.err;


    @BeforeEach
    public void setUpStreamsAndEmptyFile() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(initialOut);
        System.setErr(initialErr);
    }

    @Test
    public void loadData_fileNotFound_doesNotThrowException() {
        String filePath = "./data/temp.text";
        Storage storage = new Storage(filePath);
        TaskList taskList = new TaskList();
        HashMap<LocalDate, ArrayList<Task>> dateTasks = new HashMap<>();
        assertDoesNotThrow(() -> storage.loadData(dateTasks, taskList));
    }

    @Test
    public void loadData_fileNotFound_createsNewFile() {
        TaskList taskList = new TaskList();
        HashMap<LocalDate, ArrayList<Task>> dateTasks = new HashMap<>();

        String filePath = folder.getRoot().getPath() + "/temp.text";
        Storage storage = new Storage(filePath);
        storage.loadData(dateTasks, taskList);

        File file = new File(filePath);
        assertTrue(file.exists());
    }

    @Test
    public void addTask_validTask_updateFile() {
        Task toAdd = new ToDo("eat lunch");
        String filePath = folder.getRoot().getPath() + "/temp.text";
        Storage storage = new Storage(filePath);
        storage.addTaskToFile(toAdd);
        String lastLine ="";
        String line;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            while ((line = reader.readLine()) != null) {
                lastLine = line;
            }
            assertEquals(lastLine, toAdd.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
