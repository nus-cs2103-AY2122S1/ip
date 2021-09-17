package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TaskListTest {
    @Test
    public void getSizeTest_requestSize_returnsSizeOfTaskList() {
        TaskList tl = new TaskList();
        tl.addTask(new Todo(false, "Test todo"));
        assertEquals(1, tl.getSize());
    }
}
