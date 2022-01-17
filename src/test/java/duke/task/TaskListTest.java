package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

public class TaskListTest {
    @Test
    public void testTaskListSize() {
        ArrayList<Task> taskList = new ArrayList<>();
        taskList.add(new Todo("borrow a book"));
        taskList.add(new Deadline("return a book", LocalDate.of(2019, 9, 28)));
        TaskList tasks = new TaskList(taskList);
        assertEquals(2, tasks.size());
    }
}
