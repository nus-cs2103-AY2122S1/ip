package duke;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import duke.stubs.TaskListStub;
import duke.task.Task;

class TaskListTest {

    @Test
    void checkDate() {
        TaskListStub taskListStub = new TaskListStub();
        ArrayList<Task> actualArray = taskListStub.checkDate(LocalDate.parse("2021-12-04"));
        assertEquals("[D][ ] Create a deadline task (by: Dec 4 2021)", actualArray.get(0).toString());
    }
}
