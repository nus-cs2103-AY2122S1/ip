package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.tasks.Todo;

public class TaskListTest {
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
    private LocalDateTime testDate = LocalDateTime.parse("22/08/2021 1830".trim(), formatter);

    @Test
    public void addTodo_oneTodo_success() {
        TaskList tl = new TaskList(new ArrayList<Task>());
        tl.addTodo("homework", false);
        Task expected = new Todo("homework", false);

        assertEquals(1, tl.getTotalTasksNumber());
        assertEquals(expected.toString(), tl.getTask(0).toString());
    }

    @Test
    public void addDeadline_oneDeadline_success() {
        TaskList tl = new TaskList(new ArrayList<Task>());
        tl.addDeadline("homework", testDate, false);
        Task expected = new Deadline("homework", testDate, false);

        assertEquals(1, tl.getTotalTasksNumber());
        assertEquals(expected.toString(), tl.getTask(0).toString());
    }

    @Test
    public void addEvent_oneEvent_success() {
        TaskList tl = new TaskList(new ArrayList<Task>());
        tl.addEvent("meeting", testDate, false);
        Task expected = new Event("meeting", testDate, false);

        assertEquals(1, tl.getTotalTasksNumber());
        assertEquals(expected.toString(), tl.getTask(0).toString());
    }
}
