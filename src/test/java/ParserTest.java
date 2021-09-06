import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import duke.DukeException;
import duke.Parser;
import duke.command.AddCommand;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.RescheduleCommand;
import duke.command.UpdateCommand;
import duke.task.TaskList;
import duke.task.Todo;

/**
 * Test Parser for correct user input parsing.
 */
public class ParserTest {

    /**
     * Exit command test.
     *
     * @throws DukeException the duke exception.
     */
    @Test
    public void checkOperation_exitCommandString_correctCommandReturned() throws DukeException {
        TaskList testList = new TaskList();
        Parser test = new Parser("bye", testList);
        assertTrue(test.checkOperation() instanceof ExitCommand);
    }

    /**
     * List command test.
     *
     * @throws DukeException the duke exception.
     */
    @Test
    public void checkOperation_listCommandString_correctCommandReturned() throws DukeException {
        TaskList testList = new TaskList();
        Parser test = new Parser("list", testList);
        assertTrue(test.checkOperation() instanceof ListCommand);

    }

    /**
     * Todo command test.
     *
     * @throws DukeException the duke exception.
     */
    @Test
    public void checkOperation_todoCommandString_correctCommandReturned() throws DukeException {
        TaskList testList = new TaskList();
        Parser test = new Parser("todo cs2103t project", testList);
        assertTrue(test.checkOperation() instanceof AddCommand);
    }

    /**
     * Deadline command test.
     *
     * @throws DukeException the duke exception.
     */
    @Test
    public void checkOperation_deadlineCommandString_correctCommandReturned() throws DukeException {
        TaskList testList = new TaskList();
        Parser test = new Parser("deadline cs2103t project /by 2021-08-27 23:59", testList);
        assertTrue(test.checkOperation() instanceof AddCommand);
    }

    /**
     * Event command test.
     *
     * @throws DukeException the duke exception.
     */
    @Test
    public void checkOperation_eventCommandString_correctCommandReturned() throws DukeException {
        TaskList testList = new TaskList();
        Parser test = new Parser("event cs2103t project /at 2021-08-27 23:59", testList);
        assertTrue(test.checkOperation() instanceof AddCommand);
    }

    /**
     * Done command test.
     *
     * @throws DukeException the duke exception.
     */
    @Test
    public void checkOperation_doneCommandString_correctCommandReturned() throws DukeException {
        TaskList testList = new TaskList();
        Parser test = new Parser("done 1", testList);
        assertTrue(test.checkOperation() instanceof DoneCommand);
    }

    /**
     * Delete command test.
     *
     * @throws DukeException the duke exception.
     */
    @Test
    public void checkOperation_deleteCommandString_correctCommandReturned() throws DukeException {
        TaskList testList = new TaskList();
        Parser test = new Parser("delete 1", testList);
        assertTrue(test.checkOperation() instanceof DeleteCommand);
    }

    /**
     * Find command test.
     *
     * @throws DukeException the duke exception.
     */
    @Test
    public void checkOperation_findCommandString_correctCommandReturned() throws DukeException {
        TaskList testList = new TaskList();
        Parser test = new Parser("find work", testList);
        assertTrue(test.checkOperation() instanceof FindCommand);
    }

    /**
     * Find command test.
     *
     * @throws DukeException the duke exception.
     */
    @Test
    public void checkOperation_noSubstringFindCommandString_dukeExceptionThrown() throws DukeException {
        TaskList testList = new TaskList();
        Parser test = new Parser("find ", testList);
        Exception exception = assertThrows(DukeException.class, test::checkOperation);

        String expectedMessage = "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }

    /**
     * Reschedule command test with date and time input.
     *
     * @throws DukeException the duke exception.
     */
    @Test
    public void checkOperation_rescheduleDateTimeCommandString_correctCommandReturned() throws DukeException {
        TaskList testList = new TaskList();
        Parser test = new Parser("reschedule 1 /to 2021-09-10 18:00", testList);
        assertTrue(test.checkOperation() instanceof RescheduleCommand);
    }

    /**
     * Reschedule command test with date input.
     *
     * @throws DukeException the duke exception.
     */
    @Test
    public void checkOperation_rescheduleDateCommandString_correctCommandReturned() throws DukeException {
        TaskList testList = new TaskList();
        Parser test = new Parser("reschedule 1 /to 2021-09-10", testList);
        assertTrue(test.checkOperation() instanceof RescheduleCommand);
    }


    /**
     * Reschedule command test with invalid index.
     *
     * @throws DukeException the duke exception.
     */
    @Test
    public void checkOperation_noIdxRescheduleCommandString_dukeExceptionThrown() throws DukeException {
        TaskList testList = new TaskList();
        Parser test = new Parser("reschedule /to 2021-09-10", testList);
        Exception exception = assertThrows(DukeException.class, test::checkOperation);

        String expectedMessage = "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }

    /**
     * Update command test with proper inputs.
     *
     * @throws DukeException the duke exception.
     */
    @Test
    public void checkOperation_updateCommandString_correctCommandReturned() throws DukeException {
        TaskList testList = new TaskList();
        Parser test = new Parser("update 1 /to hello there", testList);
        assertTrue(test.checkOperation() instanceof UpdateCommand);
    }

    /**
     * Update command with no new description.
     *
     * @throws DukeException the duke exception.
     */
    @Test
    public void checkOperation_updateCommandEmptyInputString_correctCommandReturned() throws DukeException {
        TaskList testList = new TaskList();
        Parser test = new Parser("update 1 /to ", testList);
        Exception exception = assertThrows(DukeException.class, test::checkOperation);

        String expectedMessage = "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }


    /**
     * Update command with no index given.
     *
     * @throws DukeException the duke exception.
     */
    @Test
    public void checkOperation_updateCommandNoIdxString_correctCommandReturned() throws DukeException {
        TaskList testList = new TaskList();
        Parser test = new Parser("update /to test test test", testList);
        Exception exception = assertThrows(DukeException.class, test::checkOperation);

        String expectedMessage = "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }

    /**
     * Test exit command with incorrect input.
     */
    @Test
    public void checkOperation_invalidExitCommandString_dukeExceptionThrown() {
        TaskList testList = new TaskList();
        Parser test = new Parser("bye bye", testList);
        Exception exception = assertThrows(DukeException.class, test::checkOperation);

        String expectedMessage = "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }

    /**
     * Test deadline command without date or datetime.
     */
    @Test
    public void checkOperation_noDateDeadlineCommandString_dukeExceptionThrown() {
        TaskList testList = new TaskList();
        Parser test = new Parser("deadline work /by ", testList);
        Exception exception = assertThrows(DukeException.class, test::checkOperation);

        String expectedMessage = "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }

    /**
     * Test deadline command with incorrect marker 'at'.
     */
    @Test
    public void checkOperation_incorrectMarkerDeadlineCommandString_dukeExceptionThrown() {
        TaskList testList = new TaskList();
        Parser test = new Parser("deadline work /at 2021-08-27 23:59", testList);
        Exception exception = assertThrows(DukeException.class, test::checkOperation);

        String expectedMessage = "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }

    /**
     * Test event command without date or datetime.
     */
    @Test
    public void checkOperation_noDateEventCommandString_dukeExceptionThrown() {
        TaskList testList = new TaskList();
        Parser test = new Parser("event work /at ", testList);
        Exception exception = assertThrows(DukeException.class, test::checkOperation);

        String expectedMessage = "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }

    /**
     * Test event command with incorrect marker 'by'.
     */
    @Test
    public void checkOperation_incorrectMarkerEventCommandString_dukeExceptionThrown() {
        TaskList testList = new TaskList();
        Parser test = new Parser("event work /by 2021-08-27 23:59", testList);
        Exception exception = assertThrows(DukeException.class, test::checkOperation);

        String expectedMessage = "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }

    /**
     * Test event command without description.
     */
    @Test
    public void checkOperation_noDescriptionEventCommandString_dukeExceptionThrown() {
        TaskList testList = new TaskList();
        Parser test = new Parser("event /at 2021-08-27 23:59", testList);
        Exception exception = assertThrows(DukeException.class, test::checkOperation);

        String expectedMessage = "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }

    /**
     * Test deadline command without description.
     */
    @Test
    public void checkOperation_noDescriptionDeadlineCommandString_dukeExceptionThrown() {
        TaskList testList = new TaskList();
        Parser test = new Parser("deadline /by 2021-08-27 23:59", testList);
        Exception exception = assertThrows(DukeException.class, test::checkOperation);

        String expectedMessage = "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }

    /**
     * Test done command with negative index.
     */
    @Test
    public void checkOperation_negativeIndexDoneCommandString_dukeExceptionThrown() {
        TaskList testList = new TaskList();
        testList.add(new Todo("work"));
        Parser test = new Parser("done -1", testList);
        Exception exception = assertThrows(DukeException.class, test::checkOperation);

        String expectedMessage = "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }

    /**
     * Test done command with index out of bounds.
     */
    @Test
    public void checkOperation_indexOutOfBoundsDoneCommandString_illegalArgumentExceptionThrown() {
        TaskList testList = new TaskList();
        Parser test = new Parser("done 2", testList);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> test.checkOperation().execute());

        String expectedMessage = "☹ OOPS!!! Index entered is not valid. "
                + "Please use 'list' and check for the appropriate index for task(s).";
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }

    /**
     * Test delete command with negative index.
     */
    @Test
    public void checkOperation_negativeIndexDeleteCommandString_dukeExceptionThrown() {
        TaskList testList = new TaskList();
        testList.add(new Todo("work"));
        Parser test = new Parser("delete -1", testList);
        Exception exception = assertThrows(DukeException.class, test::checkOperation);

        String expectedMessage = "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }

    /**
     * Test delete command with index out of bounds.
     */
    @Test
    public void checkOperation_indexOutOfBoundsDeleteCommandString_illegalArgumentExceptionThrown() {
        TaskList testList = new TaskList();
        Parser test = new Parser("delete 2", testList);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> test.checkOperation().execute());

        String expectedMessage = "☹ OOPS!!! Index entered is not valid. "
                + "Please use 'list' and check for the appropriate index for task(s).";
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }
}
