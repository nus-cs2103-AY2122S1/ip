package duke.command;

import duke.exception.IncompleteDescriptionException;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AddCommandTest {
    AddCommand addTodo = new AddCommand("TODO", "borrow book");
    AddCommand emptyEvent = new AddCommand("EVENT", "");
    AddCommand emptyDeadline = new AddCommand("DEADLINE", "");
    Ui ui = new Ui();
    Storage storage = new Storage("data/tasks.txt");
    TaskList taskList = new TaskList();

    @Test
    public void isExitTest() {
        assertEquals(false, addTodo.isExit());
    }

    @Test
    public void eventExecuteTest() {
        assertThrows(IncompleteDescriptionException.class, () -> emptyEvent.execute(taskList, ui, storage));
    }

    @Test
    public void deadlineExecuteTest() {
        assertThrows(IncompleteDescriptionException.class, () -> emptyEvent.execute(taskList, ui, storage));
    }

}
