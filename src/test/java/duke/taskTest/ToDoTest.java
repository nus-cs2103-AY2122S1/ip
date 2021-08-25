package duke.taskTest;

import duke.task.Task;
import duke.task.ToDo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class ToDoTest {

    @Test
    public void toStringTest() {
        String exp = "[T] [ ] read book";
        String act = new ToDo("read book").toString();
        assertEquals(exp, act);
    }

    @Test
    public void getStatusTest() {
        Task todo = new ToDo("read book");
        todo.markAsDone();
        assertTrue(todo.getStatus());
    }

}
