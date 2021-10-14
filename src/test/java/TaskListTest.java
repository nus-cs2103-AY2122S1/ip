import duke.data.TaskList;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

class TaskListTest {
    @Test
    public void test_addTask() {
        ToDo todo = new ToDo("read book");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        Deadline deadline = new Deadline("return book", LocalDateTime.parse("2021-09-03 18:00", formatter));
        Event event = new Event("project meeting", LocalDateTime.parse("2021-08-30 19:00", formatter));

        TaskList taskList = new TaskList();
        taskList.addTask(todo);
        taskList.addTask(deadline);
        taskList.addTask(event);

        ArrayList<Task> expected = new ArrayList<>();
        expected.add(todo);
        expected.add(deadline);
        expected.add(event);

        assertEquals(expected, taskList.getList());
    }

    @Test
    public void test_deleteTask() {
        ToDo todo = new ToDo("read book");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        Deadline deadline = new Deadline("return book", LocalDateTime.parse("2021-09-03 18:00", formatter));
        Event event = new Event("project meeting", LocalDateTime.parse("2021-08-30 19:00", formatter));

        TaskList taskList = new TaskList();
        taskList.addTask(todo);
        taskList.addTask(deadline);
        taskList.addTask(event);
        taskList.deleteTask(todo);

        ArrayList<Task> expected = new ArrayList<>();
        expected.add(todo);
        expected.add(deadline);
        expected.add(event);
        expected.remove(todo);

        assertEquals(expected, taskList.getList());
    }
}
