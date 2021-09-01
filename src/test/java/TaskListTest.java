import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.junit.jupiter.api.Test;

import duke.DukeException;
import duke.TaskList;
import duke.task.Deadline;
import duke.task.Todo;


public class TaskListTest {

    /**
     * Tests whether a todo task can be added successfully
     */
    @Test
    public void addTask_differentType() {
        try {
            assertEquals((new Todo("Watch TV")).toString(), this.createTask("todo Watch TV", new TaskList()));
        } catch (DukeException e) {

        }
    }

    /**
     * Tests whether a wrong todo task can be added successfully
     */
    @Test
    public void addTask_todoTask_wrongInput() {
        try {
            this.createTask("Watch TV", new TaskList());
        } catch (DukeException e) {
            assertEquals("    ____________________________________________________________\n"
                    + "     ☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n"
                    + "    ____________________________________________________________", e.toString());
        }

    }

    /**
     * Tests whether a deadline task can be added successfully
     */
    @Test
    public void addTask_deadlineType() {
        try {
            assertEquals((new Deadline("Watch TV", LocalDateTime.of
                    (LocalDate.of(2021, 8, 23), LocalTime.of(14, 00))))
                    .toString(), this.createTask("deadline Watch TV /by 23/08/2021 14:00", new TaskList()));
        } catch (DukeException e) {

        }

    }

    /**
     * Tests whether a deadline task without time can be added successfully
     */
    @Test
    public void addTask_deadlineType_emptyTime() {
        try {
            this.createTask("deadline Watch TV /by ", new TaskList());
        } catch (DukeException e) {
            assertEquals("    ____________________________________________________________\n"
                    + "     ☹ OOPS!!! The description and time of a deadline cannot be empty.\n"
                    + "    ____________________________________________________________", e.toString());
        }

    }

    private String createTask(String n, TaskList taskList) throws DukeException {
        taskList.addTask(n);
        return taskList.getLast().toString();
    }
}
