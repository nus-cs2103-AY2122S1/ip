import duke.TaskList;
import duke.Task;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class TaskListTest {
    @Test
    public void confirmTaskAdded() {
        TaskList temp = new TaskList("./test/junit.txt");
        LocalDate d = LocalDate.parse("2015-10-20");
        String desc = "read a book";
        temp.addDeadline(desc, d);
        Task task = temp.get(0);
        assertEquals("[D][ ] read a book by: 2015-10-20", task.toString());
    }

    @Test
    public void checkNumberOfTasks() {
        TaskList temp = new TaskList("./test/junit.txt");
        LocalDate d1 = LocalDate.parse("2015-10-20");
        for (int i = 0; i < 10; i++) {
            temp.addDeadline("read a book", d1);
        }
        assertEquals(10 , temp.size());
    }
}