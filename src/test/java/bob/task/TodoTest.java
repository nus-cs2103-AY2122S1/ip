package bob.task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void newTodoTest() {
        assertEquals("[T] [ ] eat bread", new Todo("eat bread").printTask());
    }

    @Test
    public void newCompletedTodoTest() {
        Todo testTodo = new Todo("eat bread");
        testTodo.markCompleted();
        assertEquals("[T] [X] eat bread", testTodo.printTask());
    }
}
