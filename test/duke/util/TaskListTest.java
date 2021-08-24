package duke.util;

import duke.exception.DukeException;
import duke.task.Event;
import duke.task.Task;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TaskListTest {

    @Test
    void addTask() {
        TaskList tasks = new TaskList();
        assertEquals("Got it. I've added this task:\n"
                        + "\t[E] [ ] Test CommandAdd Event (at: Jan 1 2022, 18:00 - Jan 1 2022, 19:00)\n"
                        + "Now you have 1 tasks in the list.\n",
                tasks.addTask(new Event("Test CommandAdd Event",
                        LocalDateTime
                                .parse("2022/01/01 18:00", DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm")),
                        LocalDateTime
                                .parse("2022/01/01 19:00", DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm")))));
    }

    @Test
    void markTaskDone() throws DukeException {
        TaskList tasks = new TaskList();
        tasks.addTask(new Task("todo 1"));
        assertEquals("Nice! I've marked this task as done:\n"
                + "\t[T] [X] todo 1\n",
                tasks.markTaskDone(1));
    }

    @Test
    void deleteTask() throws DukeException {
        TaskList tasks = new TaskList();
        tasks.addTask(new Task("todo 1"));
        assertEquals("Noted. I've removed this task:\n"
                        + "\t[T] [ ] todo 1\n"
                        + "Now you have 0 tasks in the list.\n",
                tasks.deleteTask(1));
    }


}