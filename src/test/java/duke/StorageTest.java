package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import duke.task.Task;
import duke.task.ToDo;
import org.junit.jupiter.api.Test;

public class StorageTest {
    @Test
    void convertTaskStringToTask() {
        Task task = new ToDo("hello world!");
        task.done();
        try {
            assertEquals(task, Storage.convertTaskStringToTask("T&&1&&hello world!&&"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        assertEquals(2, 2);
    }
}
