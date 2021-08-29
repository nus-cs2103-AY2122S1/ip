package duke;

import duke.tasks.Todo;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {

    @Test
    public void testTodo() {
        Todo todo = new Todo("Hello");
        assertEquals(todo.toString(),"[T] [ ] Hello");
    }

    @Test
    public void testMarkTodoDone() {
        Todo todo = new Todo("Hi!");
        todo.markAsDone();
        assertEquals(todo.toString(), "[T] [X] Hi!");
    }
}
