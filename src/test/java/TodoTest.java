import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.task.Todo;

/**
 * Test cases for Todo.
 */
public class TodoTest {

    /**
     * Test constructing a Todo.
     */
    @Test
    public void todo_description_correctStringRepresentation() {
        Todo test = new Todo("test string");
        assertEquals(test.toString(), "[T][ ] test string");
    }

    /**
     * Test marking a Todo done.
     */
    @Test
    public void markDone_todo_correctStringRepresentation() {
        Todo test = new Todo("test string");
        test.markDone();
        assertEquals(test.toString(), "[T][X] test string");
    }
}
