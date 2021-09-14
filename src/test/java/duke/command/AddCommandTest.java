package duke.command;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import duke.Storage;
import duke.Ui;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.TaskList;
import duke.task.Todo;

public class AddCommandTest {

    private Storage storage = new Storage("data/duke.txt");
    private TaskList taskList = new TaskList(new ArrayList<>());
    private Ui ui = new Ui();

    /**
     * Tests AddCommand constructor for todo.
     */
    @Test
    public void testAddTodoCommand() {
        AddCommand cmd = new AddCommand("Do homework", "todo");
        cmd.execute(taskList, ui, storage);
        Todo todo = new Todo("Do homework");
        assertEquals(1, taskList.length());
        assertEquals(todo.toString(), taskList.getTask(0).toString());
    }

    /**
     * Tests AddCommand constructor for deadline.
     */
    @Test
    public void testAddDeadlineCommand() {
        AddCommand cmd = new AddCommand("Essay submission", "2021-09-09", "deadline");
        cmd.execute(taskList, ui, storage);
        Deadline deadline = new Deadline("Essay submission", "2021-09-09");
        assertEquals(1, taskList.length());
        assertEquals(deadline.toString(), taskList.getTask(0).toString());
    }

    /**
     * Tests AddCommand constructor for event.
     */
    @Test
    public void testAddEventCommand() {
        AddCommand cmd = new AddCommand("Hackathon kick-off", "2021-09-22", "19:00", "event");
        cmd.execute(taskList, ui, storage);
        Event event = new Event("Hackathon kick-off", "2021-09-22", "19:00");
        assertEquals(1, taskList.length());
        assertEquals(event.toString(), taskList.getTask(0).toString());
    }

}
