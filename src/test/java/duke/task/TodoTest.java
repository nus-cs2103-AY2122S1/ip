package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

class TodoTest {

    private static final String DESCRIPTION = "task description";

    private final Todo todo = new Todo(DESCRIPTION);

    @Test
    void getTaskType_todo_stringT() {
        assertEquals("T", todo.getTaskType());
    }

    @Test
    void toString_todo_formattedDescription() {
        assertEquals("[T][ ] " + DESCRIPTION, todo.toString());
    }

    @Test
    void equals_sameTodo_true() {
        assertEquals(new Todo(DESCRIPTION), todo);
    }

    @Test
    void equals_differentTodo_false() {
        Todo doneTodo = new Todo(DESCRIPTION);
        doneTodo.markAsDone();

        Todo differentDescriptionTodo = new Todo("other");

        assertNotEquals(todo, doneTodo);
        assertNotEquals(todo, differentDescriptionTodo);
    }
}
