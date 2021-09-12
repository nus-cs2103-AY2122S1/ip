package yoyo.core;

import org.junit.jupiter.api.Test;
import yoyo.exception.YoyoException;
import yoyo.task.Deadline;
import yoyo.task.Event;
import yoyo.task.Task;
import yoyo.task.TaskList;
import yoyo.task.Todo;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class StorageTest {
    @Test
    public void storage_readTasksFromFile_success() {
        Storage storage = new Storage("src/test/storage-read-test.txt");

        ArrayList<Task> taskArray = new ArrayList<>();
        taskArray.add(new Deadline("geq",
                LocalDateTime.parse("2018-11-11T17:00"), true));
        taskArray.add(new Event("open house",
                LocalDateTime.parse("1919-11-11T17:00"), true));
        taskArray.add(new Todo("sleep", false));

        TaskList tasks = new TaskList(taskArray);

        try {
            TaskList loaded = storage.load();
            assertTrue(tasks.equals(loaded));
        } catch (YoyoException e) {
            fail();
        }

    }

    @Test
    public void storage_writeTasksToFile_success() {
        Storage storage = new Storage("src/test/storage-write-test.txt");

        ArrayList<Task> taskArray = new ArrayList<>();
        taskArray.add(new Deadline("geq",
                LocalDateTime.parse("2018-11-11T17:00"), true));
        taskArray.add(new Event("open house",
                LocalDateTime.parse("1919-11-11T17:00"), true));
        taskArray.add(new Todo("sleep", false));

        TaskList tasks = new TaskList(taskArray);
        storage.depositTask(tasks);

        try {
            TaskList loaded = storage.load();
            assertTrue(tasks.equals(loaded));
            storage.depositTask(new TaskList(new ArrayList<>()));
        } catch (YoyoException e) {
            fail();
        }
    }
}
