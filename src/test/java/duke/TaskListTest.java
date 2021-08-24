package duke;

import duke.TaskList;
import duke.stubs.DeadlineStub;
import duke.stubs.EventStub;
import duke.stubs.TodoStub;
import duke.task.Task;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class TaskListTest {

    @Test
    void checkDate() {
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new TodoStub());
        tasks.add(new DeadlineStub());
        tasks.add(new EventStub());
        TaskList taskList = new TaskList(tasks);

        ArrayList<Task> actualArray = taskList.checkDate(LocalDate.parse("2021-12-04"));

        assertEquals("[D][ ] Create a deadline task (by: Dec 4 2021)", actualArray.get(0).toString());
    }
}