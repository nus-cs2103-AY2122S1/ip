package duke.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.task.Todo;


public class TodoTest {
    private final Todo todoIncomplete = new Todo("test 1", false);
    private final Todo todoComplete = new Todo("test 2", true);
    @Test
    public void toString_incompleteTask_success() {
        assertEquals("[T][] test 1", todoIncomplete.toString());
    }

    @Test
    public void toString_completeTask_success() {
        assertEquals("[T][X] test 2", todoComplete.toString());
    }
}
