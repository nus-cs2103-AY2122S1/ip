package yoyo.core;

import org.junit.jupiter.api.Test;
import yoyo.task.Deadline;
import yoyo.task.Event;
import yoyo.task.Task;
import yoyo.task.Todo;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {
    private static final LocalDateTime sampleTime = LocalDateTime.
            of(2021, 7, 11, 18, 11);

    @Test
    public void tasks_markDone_success() {
        Task todo = new Todo("test");
        assertEquals("[]", todo.printCompletionStatus());
        todo.toggleDone();
        assertEquals("[X]", todo.printCompletionStatus());
    }

    @Test
    public void tasks_showReadAndWrite_success() {
        Task deadline = new Deadline("test", sampleTime);
        Task event = new Event("test", sampleTime);
        assertEquals("[D][] test (by: 2021-07-11 18:11)", deadline.showStatus());
        assertEquals("[D][], test, 2021-07-11T18:11", deadline.showStatusWrite());
        assertEquals("[E][] test (at: 2021-07-11 18:11)", event.showStatus());
        assertEquals("[E][], test, 2021-07-11T18:11", event.showStatusWrite());
    }

    @Test
    public void tasks_tagging_success() {
        Task deadline = new Event("test", sampleTime);
        deadline.addTag("tag1");
        deadline.addTag("tag2");
        assertEquals("[E][] test (at: 2021-07-11 18:11) #tag1 #tag2", deadline.showStatus());
        assertEquals("[E][], test, 2021-07-11T18:11, tag1, tag2", deadline.showStatusWrite());
    }

}
