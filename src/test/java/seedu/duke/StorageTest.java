package seedu.duke;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.duke.task.Task;
import seedu.duke.task.TaskList;
import seedu.duke.task.ToDo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class StorageTest {

    @TempDir
    File tempFolder;

    @Test
    public void loadData_fileNotFound_doesNotThrowException() {
        String filePath = tempFolder.getPath() + "/temp.text";
        TaskList taskList = new TaskList();
        HashMap<LocalDate, ArrayList<Task>> dateTasks = new HashMap<>();
        Storage storage = new Storage(filePath, dateTasks);
        assertDoesNotThrow(() -> storage.loadData(taskList));
    }

    @Test
    public void loadData_fileNotFound_createsNewFile() {
        TaskList taskList = new TaskList();
        HashMap<LocalDate, ArrayList<Task>> dateTasks = new HashMap<>();

        String filePath = tempFolder.getPath() + "/temp.text";
        Storage storage = new Storage(filePath, dateTasks);
        storage.loadData(taskList);

        File file = new File(filePath);
        assertTrue(file.exists());
    }

    @Test
    public void addTask_validTask_updateFile() {
        Task toAdd = new ToDo("eat lunch");
        String filePath = tempFolder.getPath() + "/temp.text";
        HashMap<LocalDate, ArrayList<Task>> dateTasks = new HashMap<>();
        Storage storage = new Storage(filePath, dateTasks);
        storage.addTaskToFile(toAdd);
        String lastLine = "";
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
