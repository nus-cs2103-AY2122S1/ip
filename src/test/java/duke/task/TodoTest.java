package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {

    @Test
    public void testTodoConstructor() {
        Todo todo = new Todo("Send reminder emails");
        String expected = "[T][ ]" + " Send reminder emails";
        assertEquals(expected, todo.toString());
    }

    @Test
    public void testMarkTodoAsDone() {
        Todo todo = new Todo("Send reminder emails");
        todo.markAsDone();
        String expected = "[T][X]" + " Send reminder emails";
        assertEquals(expected, todo.toString());
    }


}
