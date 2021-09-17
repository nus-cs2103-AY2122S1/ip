package duke.data.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TaskTest {
    @Test
    public void isDone_success() {
        Task todo = new Todo("read book", new String[0]);
        assertEquals(false, todo.isDone());
        todo.markAsDone();
        assertEquals(true, todo.isDone());
    }
}
