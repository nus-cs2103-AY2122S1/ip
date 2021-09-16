package duke.tasktest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import duke.tasks.Task;
import duke.tasks.ToDo;


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
