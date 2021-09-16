package task;

import duke.task.Task;
import duke.task.TaskType;
import duke.task.ToDo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {
    @Test
    public void toStringTest() {
        Task toDo = new ToDo(TaskType.TODO,"Vacuum room floor");
        assertEquals("[T] [ ] Vacuum room floor", toDo.toString());
    }
}
