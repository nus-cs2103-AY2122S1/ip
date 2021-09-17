import duke.task.Todo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {

    static final String DESCRIPTION = "I am a todo task";

    @Test
    public void testStringConversion_done() {
        Todo task = new Todo(DESCRIPTION, true);
        String expectedOutput = "[T][X] I am a todo task";
        assertEquals(expectedOutput, task.toString());
    }

    @Test
    public void testStringConversion_undone() {
        Todo task = new Todo(DESCRIPTION, false);
        String expectedOutput = "[T][] I am a todo task";
        assertEquals(expectedOutput, task.toString());
    }

    @Test
    public void testStringWriteConversion() {
        Todo task = new Todo(DESCRIPTION, true);
        String expectedOutput = "todo | 1 | I am a todo task";
        assertEquals(expectedOutput, task.toWriteString());
    }
}
