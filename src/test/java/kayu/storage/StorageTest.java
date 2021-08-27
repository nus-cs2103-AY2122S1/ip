package kayu.storage;

import static kayu.storage.Storage.INVALID_TASK_FORMAT;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import kayu.exception.StorageException;
import kayu.task.Deadline;
import kayu.task.Event;
import kayu.task.Task;
import kayu.task.Todo;

public class StorageTest {
    
    private static final String RESOURCE_PATH = "src/test/resources";
    private static final String FILE_PATH = RESOURCE_PATH + "/storage_test_default.txt";
    private static final List<Task> TASKS = new ArrayList<>();
    private Storage storage;
    
    @BeforeAll
    public static void setTaskList() {
        TASKS.clear();
        TASKS.add(new Todo("test this program", true));
        TASKS.add(new Deadline(
                "do this",
                LocalDate.parse("2020-10-10"),
                LocalTime.parse("10:30")));
        TASKS.add(new Event(
                "run there",
                true,
                LocalDate.parse("2020-10-16"),
                LocalTime.parse("21:30")));
    }
    
    @BeforeEach
    public void setUp() {
        storage = new Storage();
        storage.setDirectoryAndFilePath(FILE_PATH);
    }
    
    @AfterEach
    public void reset() throws IOException {
        Path filePath = Paths.get(FILE_PATH);
        List<String> taskLines = TASKS.stream()
                .map(Task::toEncodedString)
                .collect(Collectors.toList());
        Files.write(filePath, taskLines);
    }
    
    @Test
    public void testLoad() throws StorageException {
        List<Task> tasks = storage.load();
        assertEquals(3, tasks.size());

        for (int idx = 0; idx < tasks.size(); idx++) {
            Task loadedTask = tasks.get(idx);
            Task expectedTask = TASKS.get(idx);
            assertEquals(expectedTask.toString(), loadedTask.toString());
        }
    }
    
    @Test
    public void testSave() throws StorageException, IOException {
        List<Task> newTasks = new ArrayList<>();
        newTasks.add(new Todo("test 1"));
        newTasks.add(new Todo("test 2"));
        
        storage.saveTasks(newTasks);
        Path filePath = Paths.get(FILE_PATH);
        List<String> saved = Files.readAllLines(filePath);
        assertEquals(newTasks.size(), saved.size());
        
        for (int idx = 0; idx < newTasks.size(); idx++) {
            Task task = newTasks.get(idx);
            String encoded = saved.get(idx);
            assertEquals(task.toEncodedString(), encoded);
        }
    }
    
    @Test
    public void load_formatIncorrect_throwsException() {
        String newFilePath = RESOURCE_PATH + "/storage_test_incorrect.txt";
        String problematicLine = "T # 1 ? test this program";
        storage.setDirectoryAndFilePath(newFilePath);
        try {
            storage.load();
            fail();
            
        } catch (StorageException exception) {
            String expected = String.format(INVALID_TASK_FORMAT, problematicLine);
            assertEquals(expected, exception.getMessage());
        }
    }
}
