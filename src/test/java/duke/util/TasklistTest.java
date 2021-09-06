package duke.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.exception.DukeException;

public class TasklistTest {

    @Test
    public void testAddTask_deadlineTask_returnSuccessMessage() {
        Tasklist temp = new Tasklist();
        String message = null;
        try {
            message = temp.addTask("test123 /by 24-07-2010 19:00", "deadline");
        } catch (DukeException e) {
            message = e.toString();
        }
        String successMessage = "Got it. I've added this task:\n"
                + "  [D][ ] test123 (by: 24-Jul-2010 19:00)\n"
                + "Now you have 1 tasks in the list.";
        assertEquals(message, successMessage);

    }

    @Test
    public void testAddTask_invalidTask_returnInvalidCommandExceptionMessage() {
        Tasklist temp = new Tasklist();
        String message = null;
        try {
            message = temp.addTask("Invalid Command", "sup");
        } catch (DukeException e) {
            message = e.toString();
        }
        assertEquals(message, "Hi I don't understand your command :(");

    }

    @Test
    public void testAddTask_invalidDate_returnInvalidArgumentExceptionMessage() {
        Tasklist temp = new Tasklist();
        String message = null;
        try {
            message = temp.addTask("test123 /at 12:00", "event");
        } catch (DukeException e) {
            message = e.toString();
        }
        assertEquals(message, "Hi, the times for the event is invalid!");

    }

    @Test
    public void testDeleteTask_deleteTask_returnSuccessMessage() {
        Tasklist temp = new Tasklist();
        String message = null;
        try {
            temp.addTask("test123 /at 24-07-2010 19:00 to 24-Dec-2010 00:00", "event");
            temp.addTask("test1", "todo");
            message = temp.deleteTask(1);
        } catch (DukeException e) {
            message = e.toString();
        }
        String deleteMessage = "Noted. I've removed this task:\n"
                + "  [E][ ] test123 (at: 24-Jul-2010 19:00 to 24-Dec-2010 00:00)\n"
                + "Now you have 1 tasks in the list.";
        assertEquals(message, deleteMessage);

    }

    @Test
    public void testUpdateTask_updateInvalidTaskNumber_returnErrorMessage() {
        Tasklist temp = new Tasklist();
        String message = null;
        try {
            temp.addTask("test123 /at 24-07-2010 19:00 to 24-Dec-2010 00:00", "event");
            temp.addTask("test1", "todo");
            message = temp.updateDescription(3, "shagadoodles");
        } catch (DukeException e) {
            message = e.toString();
        }
        String deleteMessage = "Hi, 3 is not a valid index. List has currently 2 items.";
        assertEquals(message, deleteMessage);

    }

    @Test
    public void testUpdateTask_updateTaskDesc_returnSuccessMessage() {
        Tasklist temp = new Tasklist();
        String message = null;
        try {
            temp.addTask("test123 /at 24-07-2010 19:00 to 24-Dec-2010 00:00", "event");
            temp.addTask("test1", "todo");
            message = temp.updateDescription(1, "shagadoodles");
        } catch (DukeException e) {
            message = e.toString();
        }
        String deleteMessage = "Nice! I've changed the description of the task:\n"
                + "  [E][ ] shagadoodles (at: 24-Jul-2010 19:00 to 24-Dec-2010 00:00)";
        assertEquals(message, deleteMessage);

    }

    @Test
    public void testUpdateTask_updateToDoDate_returnErrorMessage() {
        Tasklist temp = new Tasklist();
        String message = null;
        try {
            temp.addTask("test123 /at 24-07-2010 19:00 to 24-Dec-2010 00:00", "event");
            temp.addTask("test1", "todo");
            message = temp.updateStartDateTime(2, "2020-Jan-23 21:00");
        } catch (DukeException e) {
            message = e.toString();
        }
        String deleteMessage = "Hi, the times for the todo is invalid!";
        assertEquals(message, deleteMessage);

    }

    @Test
    public void testUpdateTask_updateDeadlineStartDate_returnErrorMessage() {
        Tasklist temp = new Tasklist();
        String message = null;
        try {
            temp.addTask("test123 /at 24-07-2010 19:00 to 24-Dec-2010 00:00", "event");
            temp.addTask("test1 /by 2021-10-23", "deadline");
            message = temp.updateStartDateTime(2, "2020-Jan-23 21:00");
        } catch (DukeException e) {
            message = e.toString();
        }
        String deleteMessage = "Hi, the times for the deadline is invalid!";
        assertEquals(message, deleteMessage);

    }

    @Test
    public void testUpdateTask_updateTaskStartDateTime_returnSuccessMessage() {
        Tasklist temp = new Tasklist();
        String message = null;
        try {
            temp.addTask("test123 /at 24-07-2010 19:00 to 24-Dec-2010 00:00", "event");
            temp.addTask("test1", "todo");
            message = temp.updateStartDateTime(1, "2021-Jul-25 08:00");
        } catch (DukeException e) {
            message = e.toString();
        }
        String deleteMessage = "Nice! I've changed the start time of the task:\n"
                + "  [E][ ] test123 (at: 25-Jul-2021 08:00 to 24-Dec-2010 00:00)";
        assertEquals(message, deleteMessage);

    }

    @Test
    public void testUpdateTask_updateTaskEndDateTime_returnSuccessMessage() {
        Tasklist temp = new Tasklist();
        String message = null;
        try {
            temp.addTask("test123 /at 24-07-2010 19:00 to 24-Dec-2010 00:00", "event");
            temp.addTask("test1", "todo");
            message = temp.updateEndDateTime(1, "2021-Jul-25 08:00");
        } catch (DukeException e) {
            message = e.toString();
        }
        String deleteMessage = "Nice! I've changed the end time of the task:\n"
                + "  [E][ ] test123 (at: 24-Jul-2010 19:00 to 25-Jul-2021 08:00)";
        assertEquals(message, deleteMessage);

    }

    @Test
    public void testToString() {
        Tasklist temp = new Tasklist();
        try {
            temp.addTask("test1", "todo");
            temp.addTask("test12 /by 24-07-2010 19:00", "deadline");
            temp.addTask("test123 /at 24-07-2010 19:00 to 24-Dec-2010 00:00", "event");
        } catch (DukeException e) {

            temp = new Tasklist();

        }
        String successMessage = "1.[T][ ] test1\n"
                + "2.[D][ ] test12 (by: 24-Jul-2010 19:00)\n"
                + "3.[E][ ] test123 (at: 24-Jul-2010 19:00 to 24-Dec-2010 00:00)\n";
        assertEquals(temp.toString(), successMessage);

    }

}
