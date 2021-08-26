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

;

public class StorageTest {
    @Test
    public void readTaskFromFile_normal_success() throws YoyoException {
        Storage storage = new Storage("storage-test.txt");

        ArrayList<Task> taskArray = new ArrayList<>();
        taskArray.add(new Deadline("geq",
                LocalDateTime.parse("2018-11-11T17:00"), true));
        taskArray.add(new Event("open house",
                LocalDateTime.parse("1919-11-11T17:00"), true));
        taskArray.add(new Todo("sleep", false));

        TaskList tasks = new TaskList(taskArray);

        assertTrue(tasks.equals(storage.load()));
    }
}
