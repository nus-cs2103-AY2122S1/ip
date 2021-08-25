package yoyo.core;

import org.junit.jupiter.api.Test;
import yoyo.task.Deadline;
import yoyo.task.Event;
import yoyo.task.Task;
import yoyo.task.Todo;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class TaskTest {
    private static LocalDateTime sampleTime = LocalDateTime.
            of(2021, 7, 11, 18, 11);

    @Test
    public void completionStatus_toggleComplete_success() {
        Task todo = new Todo("test");
        assertEquals("[]", todo.printCompletionStatus());
        todo.toggleDone();
        assertEquals("[X]", todo.printCompletionStatus());
    }

    @Test
    public void showAllStatus_normalAndWrite_success() {
        Task deadline = new Deadline("test", sampleTime);
        assertEquals("[D][] test (by: 2021-07-11 18:11)", deadline.showStatus());
        assertEquals("[D][], test, 2021-07-11T18:11", deadline.showStatusWrite());
    }

}
