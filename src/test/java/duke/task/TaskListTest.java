package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;

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
