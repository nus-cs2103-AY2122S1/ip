package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class TodoTest {
    @Test
    void testToString() {
        Todo todo = new Todo("submit homework");
        todo.markAsLowPriority();
        String output = "[T][  ] \u2605submit homework";
        assertEquals(output, todo.toString());
    }
}
