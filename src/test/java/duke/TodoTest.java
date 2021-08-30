package duke;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.tasks.Todo;


public class TodoTest {

    @Test
    public void testTodo() {
        Todo todo = new Todo("Hello");
        assertEquals(todo.toString(), "[T] [ ] Hello");
    }

    @Test
    public void testMarkTodoDone() {
        Todo todo = new Todo("Hi!");
        todo.markAsDone();
        assertEquals(todo.toString(), "[T] [X] Hi!");
    }
}
