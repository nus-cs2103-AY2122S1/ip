import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.tasks.Todo;

public class TodoTest {
    @Test
    public void outputTest1() {
        Todo testTodo = new Todo(false, "homework");
        assertEquals("[T][ ] :  homework", testTodo.toString());
    }

    @Test
    public void outputTest2() {
        Todo testTodo = new Todo(false, "homework");
        testTodo.markAsDone();
        assertEquals("[T][X] :  homework", testTodo.toString());
    }
}
