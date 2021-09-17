import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import duke.DukeStorage;
import duke.TaskList;
import duke.Ui;
import duke.commands.DeadlineCommand;
import duke.commands.EventCommand;
import duke.commands.TodoCommand;

public class CommandTest {
    @Test
    public void todoCommandTest() {
        Ui ui = new Ui();
        TaskList taskList = new TaskList();
        DukeStorage storage = new DukeStorage("tasklist.txt");

        TodoCommand todo = new TodoCommand("test todo");

        todo.execute(taskList, ui, storage);
        assertEquals("1. [T][ ] test todo\n", taskList.toString());
    }

    @Test
    public void deadlineCommandTest() {
        Ui ui = new Ui();
        TaskList taskList = new TaskList();
        DukeStorage storage = new DukeStorage("tasklist.txt");

        DeadlineCommand deadline = new DeadlineCommand("test deadline",
                LocalDateTime.of(2021, 01, 12, 01, 00));

        deadline.execute(taskList, ui, storage);
        assertEquals("1. [D][ ] test deadline (by: Jan 12 2021 01:00)\n", taskList.toString());
    }

    @Test
    public void eventCommandTest() {
        Ui ui = new Ui();
        TaskList taskList = new TaskList();
        DukeStorage storage = new DukeStorage("tasklist.txt");

        EventCommand event = new EventCommand("test event",
                LocalDateTime.of(2021, 01, 12, 01, 00));

        event.execute(taskList, ui, storage);
        assertEquals("1. [E][ ] test event (at: Jan 12 2021 01:00)\n", taskList.toString());
    }
}
