import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

import duke.TaskList;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.ToDo;

public class TaskListTest {
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    @Test
    public void addTask_deadlineFormat() {
        Deadline d = new Deadline("hw due", LocalDateTime.parse("19/08/2021 19:00", formatter));
        TaskList t = new TaskList();
        t.addTask(d);
        assertEquals("[D][✘] hw due (by: Aug 19 2021 07:00pm)", t.getTask(0).toString());
    }


    @Test
    public void markDone_eventFormat() {
        Event e = new Event("class", LocalDateTime.parse("19/09/2021 19:00", formatter));
        TaskList t = new TaskList();
        t.addTask(e);
        t.markDone(0);
        assertEquals("[E][✓] class (at: Sep 19 2021 07:00pm)", t.getTask(0).toString());

    }

    @Test
    public void addTask_toDoFormat() {
        ToDo td = new ToDo("write");
        TaskList t = new TaskList();
        t.addTask(td);
        assertEquals("[T][✘] write", t.getTask(0).toString());
    }
}
