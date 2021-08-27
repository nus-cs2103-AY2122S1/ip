package util;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import side.exception.DeleteIndexException;
import side.exception.NoIndexException;
import side.exception.TaskIndexException;
import side.exception.TooManyIndexesException;
import side.exception.WrongFormatException;
import side.util.TaskList;
import side.util.Ui;

public class UiTest {
    @Test
    public void addDeadlineTest() {
        Ui ui = new Ui();
        assertThrows(WrongFormatException.class, () -> ui.addDeadline("deadline /at", new TaskList()));
    }

    @Test
    public void addEventTest() {
        Ui ui = new Ui();
        assertThrows(WrongFormatException.class, () -> ui.addDeadline("event /by", new TaskList()));
    }

    @Test
    public void addTaskTest() {
        Ui ui = new Ui();
        assertThrows(WrongFormatException.class, () -> ui.addDeadline("todo   ", new TaskList()));
    }

    @Test
    public void handleDoneTest() {
        Ui ui = new Ui();
        assertThrows(TaskIndexException.class, () -> ui.handleDone("done -1", new TaskList()));
        assertThrows(NoIndexException.class, () -> ui.handleDone("done", new TaskList()));
        assertThrows(TooManyIndexesException.class, () -> ui.handleDone("done 1 3 5", new TaskList()));
    }

    @Test
    public void handleDeleteTest() {
        Ui ui = new Ui();
        assertThrows(DeleteIndexException.class, () -> ui.handleDelete("delete -1", new TaskList()));
        assertThrows(NoIndexException.class, () -> ui.handleDelete("delete", new TaskList()));
        assertThrows(TooManyIndexesException.class, () -> ui.handleDelete("delete 1 3 5", new TaskList()));
    }
}
