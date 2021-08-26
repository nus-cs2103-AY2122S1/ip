package duke.tasklist;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    @Test
    public void testAddTaskList() {
        Todo todo = new Todo("read book");

        LocalDate by = LocalDate.parse("2021-08-30");
        Deadline deadline = new Deadline("return book", by);

        LocalDateTime at = LocalDateTime.parse("2021-08-30T13:55");
        Event event = new Event("meeting", at);

        ArrayList<Task> allTasks = new ArrayList<>();
        TaskList tasklist = new TaskList(allTasks);

        tasklist.storeTask(todo);
        tasklist.storeTask(deadline);
        tasklist.storeTask(event);

        assertEquals("[T][ ] read book", tasklist.getSpecificTask(1).toString());
        assertEquals("[D][ ] return book (by: 08 30 2021)", tasklist.getSpecificTask(2).toString());
        assertEquals("[E][ ] meeting (at: 08 30 2021 at 01:55)", tasklist.getSpecificTask(3).toString());
    }
}
