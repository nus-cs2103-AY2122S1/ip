package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Class to test todo objects.
 */
public class TodoTest {

    /**
     * Checks if completed todos are handled appropriately.
     */
    @Test
    public void completedTodoTest() {
        Todo todo = new Todo("completed task");
        todo.setCompleted();
        assertEquals("[T][X] completed task", todo.toString());
    }

    /**
     * Checks if incomplete todos are handled appropriately.
     */
    @Test
    public void incompleteTodoTest() {
        Todo todo = new Todo("incomplete task");
        assertEquals("[T][ ] incomplete task", todo.toString());
    }
}
