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
            System.out.println(e);
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
            System.out.println(e);
        }
    }


    private String createTask(String n, TaskList taskList) throws DukeException {
        taskList.addTask(n);
        return taskList.getLast().toString();
    }
}
