package lifeline.command;

import static lifeline.util.ErrorString.ERROR_DEADLINE_INCORRECT_FORMAT;
import static lifeline.util.ErrorString.ERROR_DEADLINE_MISSING_DETAILS;
import static lifeline.util.ErrorString.ERROR_DELETE_MISSING_INDEX;
import static lifeline.util.ErrorString.ERROR_DONE_MISSING_INDEX;
import static lifeline.util.ErrorString.ERROR_EVENT_INCORRECT_FORMAT;
import static lifeline.util.ErrorString.ERROR_EVENT_MISSING_DETAILS;
import static lifeline.util.ErrorString.ERROR_FIND_MISSING_KEYWORD;
import static lifeline.util.ErrorString.ERROR_INDEX_OUT_OF_BOUNDS;
import static lifeline.util.ErrorString.ERROR_NON_INTEGER_INDEX;
import static lifeline.util.ErrorString.ERROR_NO_TASKS_FOUND;
import static lifeline.util.ErrorString.ERROR_TODO_MISSING_DETAILS;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.File;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import lifeline.exception.LifelineException;
import lifeline.storage.Storage;
import lifeline.task.Task;
import lifeline.task.TaskList;
import lifeline.task.ToDo;
import lifeline.ui.Ui;

public class CommandHandlerTest {

    @Test
    public void handleToDoCommand_missingName_exceptionThrown() {
        try {
            Storage storage = new Storage("test.json");
            TaskList taskList = new TaskList(new ArrayList<Task>());
            Ui ui = new Ui();
            Command.TODO.getExecute().apply("todo", storage, taskList, ui);
            fail();
        } catch (LifelineException e) {
            assertEquals(ERROR_TODO_MISSING_DETAILS, e.getMessage());
        }
    }

    @Test
    public void handleToDoCommand_completeCommand_success() throws LifelineException {
        Storage storage = new Storage("test.json");
        TaskList taskList = new TaskList(new ArrayList<Task>());
        Ui ui = new Ui();
        Command.TODO.getExecute().apply("todo read book", storage, taskList, ui);
        assertEquals("[T][ ] read book", taskList.getTask(0).toString());
    }

    @Test
    public void handleEventCommand_missingNameDateAndTime_exceptionThrown() {
        try {
            Storage storage = new Storage("test.json");
            TaskList taskList = new TaskList(new ArrayList<Task>());
            Ui ui = new Ui();
            Command.EVENT.getExecute().apply("event", storage, taskList, ui);
            fail();
        } catch (LifelineException e) {
            assertEquals(ERROR_EVENT_MISSING_DETAILS, e.getMessage());
        }
    }

    @Test
    public void handleEventCommand_missingDateAndTime_exceptionThrown() {
        try {
            Storage storage = new Storage("test.json");
            TaskList taskList = new TaskList(new ArrayList<Task>());
            Ui ui = new Ui();
            Command.EVENT.getExecute().apply("event wedding /at", storage, taskList, ui);
            fail();
        } catch (LifelineException e) {
            assertEquals(ERROR_EVENT_INCORRECT_FORMAT, e.getMessage());
        }
    }

    @Test
    public void handleEventCommand_missingTime_exceptionThrown() {
        try {
            Storage storage = new Storage("test.json");
            TaskList taskList = new TaskList(new ArrayList<Task>());
            Ui ui = new Ui();
            Command.EVENT.getExecute().apply("event wedding /at 04/03/21", storage, taskList, ui);
            fail();
        } catch (LifelineException e) {
            assertEquals(ERROR_EVENT_INCORRECT_FORMAT, e.getMessage());
        }
    }

    @Test
    public void handleEventCommand_completeCommand_success() throws LifelineException {
        Storage storage = new Storage("test.json");
        TaskList taskList = new TaskList(new ArrayList<Task>());
        Ui ui = new Ui();
        Command.EVENT.getExecute().apply("event wedding /at 04/03/21 1400 1600", storage, taskList, ui);
        assertEquals("[E][ ] wedding (at: Mar 4 2021 2:00 PM - 4:00 PM)", taskList.getTask(0).toString());
    }


    @Test
    public void handleDeadlineCommand_missingNameDateAndTime_exceptionThrown() {
        try {
            Storage storage = new Storage("test.json");
            TaskList taskList = new TaskList(new ArrayList<Task>());
            Ui ui = new Ui();
            Command.DEADLINE.getExecute().apply("deadline", storage, taskList, ui);
            fail();
        } catch (LifelineException e) {
            assertEquals(ERROR_DEADLINE_MISSING_DETAILS, e.getMessage());
        }
    }

    @Test
    public void handleDeadlineCommand_missingDateAndTime_exceptionThrown() {
        try {
            Storage storage = new Storage("test.json");
            TaskList taskList = new TaskList(new ArrayList<Task>());
            Ui ui = new Ui();
            Command.DEADLINE.getExecute().apply("deadline project /by", storage, taskList, ui);
            fail();
        } catch (LifelineException e) {
            assertEquals(ERROR_DEADLINE_INCORRECT_FORMAT, e.getMessage());
        }
    }

    @Test
    public void handleDeadlineCommand_missingTime_exceptionThrown() {
        try {
            Storage storage = new Storage("test.json");
            TaskList taskList = new TaskList(new ArrayList<Task>());
            Ui ui = new Ui();
            Command.DEADLINE.getExecute().apply("deadline project /by 04/03/21", storage, taskList, ui);
            fail();
        } catch (LifelineException e) {
            assertEquals(ERROR_DEADLINE_INCORRECT_FORMAT, e.getMessage());
        }
    }

    @Test
    public void handleDeadlineCommand_completeCommand_success() throws LifelineException {
        Storage storage = new Storage("test.json");
        TaskList taskList = new TaskList(new ArrayList<Task>());
        Ui ui = new Ui();
        Command.DEADLINE.getExecute().apply("deadline project /by 04/03/21 1830", storage, taskList, ui);
        assertEquals("[D][ ] project (by: Mar 4 2021 6:30 PM)", taskList.getTask(0).toString());
    }

    @Test
    public void handleDoneCommand_missingIndex_exceptionThrown() {
        try {
            Storage storage = new Storage("test.json");
            TaskList taskList = new TaskList(new ArrayList<Task>());
            Ui ui = new Ui();
            Command.DONE.getExecute().apply("done", storage, taskList, ui);
            fail();
        } catch (LifelineException e) {
            assertEquals(ERROR_DONE_MISSING_INDEX, e.getMessage());
        }
    }

    @Test
    public void handleDoneCommand_indexOutOfBounds_exceptionThrown() {
        try {
            Storage storage = new Storage("test.json");
            TaskList taskList = new TaskList(new ArrayList<Task>());
            Ui ui = new Ui();
            Command.DONE.getExecute().apply("done 2", storage, taskList, ui);
            fail();
        } catch (LifelineException e) {
            assertEquals(ERROR_INDEX_OUT_OF_BOUNDS, e.getMessage());
        }
    }

    @Test
    public void handleDoneCommand_indexIsNotInteger_exceptionThrown() {
        try {
            Storage storage = new Storage("test.json");
            TaskList taskList = new TaskList(new ArrayList<Task>());
            Ui ui = new Ui();
            Command.DONE.getExecute().apply("done abc", storage, taskList, ui);
            fail();
        } catch (LifelineException e) {
            assertEquals(ERROR_NON_INTEGER_INDEX, e.getMessage());
        }
    }

    @Test
    public void handleDoneCommand_completeCommand_success() throws LifelineException {
        Storage storage = new Storage("test.json");
        ArrayList<Task> tasks = new ArrayList<>();
        Task todo = new ToDo("read book");
        tasks.add(todo);
        TaskList taskList = new TaskList(tasks);
        Ui ui = new Ui();
        Command.DONE.getExecute().apply("done 1", storage, taskList, ui);
        assertEquals("[T][X] read book", taskList.getTask(0).toString());
    }


    @Test
    public void handleDeleteCommand_missingIndex_exceptionThrown() {
        try {
            Storage storage = new Storage("test.json");
            TaskList taskList = new TaskList(new ArrayList<Task>());
            Ui ui = new Ui();
            Command.DELETE.getExecute().apply("delete", storage, taskList, ui);
            fail();
        } catch (LifelineException e) {
            assertEquals(ERROR_DELETE_MISSING_INDEX, e.getMessage());
        }
    }

    @Test
    public void handleDeleteCommand_indexOutOfBounds_exceptionThrown() {
        try {
            Storage storage = new Storage("test.json");
            TaskList taskList = new TaskList(new ArrayList<Task>());
            Ui ui = new Ui();
            Command.DELETE.getExecute().apply("delete 2", storage, taskList, ui);
            fail();
        } catch (LifelineException e) {
            assertEquals(ERROR_INDEX_OUT_OF_BOUNDS, e.getMessage());
        }
    }

    @Test
    public void handleDeleteCommand_indexIsNotInteger_exceptionThrown() {
        try {
            Storage storage = new Storage("test.json");
            TaskList taskList = new TaskList(new ArrayList<Task>());
            Ui ui = new Ui();
            Command.DELETE.getExecute().apply("delete abc", storage, taskList, ui);
            fail();
        } catch (LifelineException e) {
            assertEquals(ERROR_NON_INTEGER_INDEX, e.getMessage());
        }
    }

    @Test
    public void handleDeleteCommand_completeCommand_success() throws LifelineException {
        Storage storage = new Storage("test.json");
        ArrayList<Task> tasks = new ArrayList<>();
        Task todo = new ToDo("read book");
        tasks.add(todo);
        TaskList taskList = new TaskList(tasks);
        Ui ui = new Ui();
        Command.DELETE.getExecute().apply("delete 1", storage, taskList, ui);
        assertTrue(taskList.getSize() == 0);
    }

    @Test
    public void testHandleListCommand() throws LifelineException {
        Storage storage = new Storage("test.json");
        ArrayList<Task> tasks = new ArrayList<>();
        Task todo = new ToDo("read book");
        tasks.add(todo);
        TaskList taskList = new TaskList(tasks);
        Ui ui = new Ui();
        assertEquals("Here is your task:\n" + "1. [T][ ] read book\n" + "You have 1 uncompleted task.\n",
                Command.LIST.getExecute().apply("list", storage, taskList, ui));
    }

    @Test
    public void testHandleByeCommand() throws LifelineException {
        Storage storage = new Storage("test.json");
        TaskList taskList = new TaskList(new ArrayList<Task>());
        Ui ui = new Ui();
        assertEquals("Goodbye! Thanks for chatting with me!\n", Command.BYE.getExecute().apply("bye",
                storage, taskList, ui));
    }

    @Test
    public void handleFind_missingKeyword_exceptionThrown() {
        try {
            Storage storage = new Storage("test.json");
            ArrayList<Task> tasks = new ArrayList<>();
            Task todo = new ToDo("read book");
            tasks.add(todo);
            TaskList taskList = new TaskList(tasks);
            Ui ui = new Ui();
            Command.FIND.getExecute().apply("find", storage, taskList, ui);
        } catch (LifelineException e) {
            assertEquals(ERROR_FIND_MISSING_KEYWORD, e.getMessage());
        }
    }

    @Test
    public void handleFind_noTaskFound_exceptionThrown() {
        try {
            Storage storage = new Storage("test.json");
            ArrayList<Task> tasks = new ArrayList<>();
            Task todo = new ToDo("read book");
            tasks.add(todo);
            TaskList taskList = new TaskList(tasks);
            Ui ui = new Ui();
            Command.FIND.getExecute().apply("find abc", storage, taskList, ui);
        } catch (LifelineException e) {
            assertEquals(ERROR_NO_TASKS_FOUND, e.getMessage());
        }
    }

    @Test
    public void handleFind_completeCommandTaskFound_success() throws LifelineException {
        Storage storage = new Storage("test.json");
        ArrayList<Task> tasks = new ArrayList<>();
        Task todo = new ToDo("read book");
        tasks.add(todo);
        TaskList taskList = new TaskList(tasks);
        Ui ui = new Ui();
        String output = Command.FIND.getExecute().apply("find book", storage, taskList, ui);
        assertEquals("Task with keyword book:\n" + "[T][ ] read book\n", output);
    }

    @AfterEach
    public void deleteTestFiles() {
        File testFile = new File("test.json");
        testFile.delete();
    }
}
