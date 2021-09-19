package chad.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ToDoTaskTest {

    @Test
    public void getFileRepresentation_notDoneToDoTask_success() {
        ToDoTask toDoTask = new ToDoTask("Task description");
        assertEquals("T 0 Task description", toDoTask.getFileRepresentation());
    }

    @Test
    public void getFileRepresentation_doneToDoTask_success() {
        ToDoTask toDoTask = new ToDoTask("Task description");
        toDoTask.markDone();
        assertEquals("T 1 Task description", toDoTask.getFileRepresentation());
    }
}
