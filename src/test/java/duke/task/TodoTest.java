package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TodoTest {

    /**
     * Tests Todo constructor.
     */
    @Test
    public void testTodoConstructor() {
        Todo todo = new Todo("Send reminder emails");
        String expected = "[T][ ]" + " Send reminder emails";
        assertEquals(expected, todo.toString());
    }

    /**
     * Tests functionality of marking task as done.
     */
    @Test
    public void testMarkTodoAsDone() {
        Todo todo = new Todo("Send reminder emails");
        todo.markAsDone();
        String expected = "[T][X]" + " Send reminder emails";
        assertEquals(expected, todo.toString());
    }


}
