package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;


public class TodoTest {

    @Test
    public void getStatusIconTest() {
        Todo todo = new Todo("sleep by 5pm");
        todo.markAsDone();
        assertEquals(todo.getStatusIcon(), "\u2713");
    }
}
