package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TodoTest {

    private static final String DESCRIPTION = "task description";

    Todo todo = new Todo(DESCRIPTION);

    @Test
    void getTaskType_todo_T() {
        assertEquals("T", todo.getTaskType());
    }

    @Test
    void toString_todo_formattedDescription() {
        assertEquals("[T][ ] " + DESCRIPTION, todo.toString());
    }
}