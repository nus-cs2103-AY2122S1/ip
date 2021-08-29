import duke.exceptions.NoSuchCommandException;
import duke.tasks.Task;
import duke.tasks.ToDo;
import org.junit.jupiter.api.Test;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//import duke.tasks.ToDo;
import duke.tasks.Event;
import duke.tasks.Deadline;
import duke.exceptions.WrongTimeFormatException;
import duke.exceptions.WrongDateFormatException;

import java.time.format.DateTimeParseException;

import static org.junit.jupiter.api.Assertions.*;


public class TasksTest {

    @Test
    public void eventTask_errorDate_exceptionThrown() {
        assertThrows(WrongDateFormatException.class,
                () -> new Event("return book /at Aug 1800"));
    }

    @Test
    public void eventTask_errorTime_exceptionThrown() {
        assertThrows(DateTimeParseException.class,
                () -> new Event("return book /at 12/12/2019 6pm"));
    }

    @Test
    public void updateEventStatus_success()
            throws WrongDateFormatException, WrongTimeFormatException {
        String completed = "[X]";
        Task eventTask = new Event(
                "return book /at 12/12/2019 18:00")
                .updateStatus(completed);
        assertEquals(
                "[E][X] return book (at: DECEMBER 12 2019 6pm)",
                eventTask.toString());
    }

    @Test
    public void eventTask_storageString()
            throws WrongDateFormatException, WrongTimeFormatException {
        Task eventTask = new Event("project meeting /at 12-12-2019 18:00");
        assertEquals(
                "[E][] project meeting (at: 12-12-2019 18:00)",
                eventTask.getOriginalFormatForStorage());
    }

    @Test
    public void eventTask_success() {
        boolean willFail = false;
        try {
            Task eventTask = new Event("return book /at 12/12/2019 18:00");
            assertFalse(willFail, "Event task is indeed a success!");
        } catch (WrongTimeFormatException | WrongDateFormatException e) {
            willFail = true;
            assertFalse(willFail, "Shouldn't be failing");
        }
    }


    @Test
    public void toDo_success() {
        boolean willFail = false;
        try {
            Task todoTask = new ToDo("read book");
            assertFalse(willFail, "To Do task is a success");
        } catch (WrongTimeFormatException | WrongDateFormatException e) {
            willFail = true;
            assertFalse(willFail, "Shouldn't be failing");
        }
    }

    @Test
    public void toDo_updateStatus_Success()
            throws WrongDateFormatException, WrongTimeFormatException {
        String completed = "[X]";
        Task toDoEvent = new ToDo("read book").updateStatus(completed);
        assertEquals("[T][X] read book", toDoEvent.toString());
    }

    @Test
    public void toDoTask_updateStatus_storageString()
            throws WrongDateFormatException, WrongTimeFormatException {
        Task toDoTask = new ToDo("sleep well");
        String completed = "[X]";
        assertEquals("[T][X] sleep well",
                toDoTask
                        .updateStatus(completed)
                        .getOriginalFormatForStorage());
    }


    @Test
    public void deadlineTask_Success() {
        boolean willFail = false;
        try {
            Task deadlineTask = new Deadline("return book /by 12/12/2019 18:00");
            assertFalse(willFail, "Deadline task is a success");
        } catch (WrongTimeFormatException | WrongDateFormatException e) {
            willFail = true;
            assertFalse(willFail, "Shouldn't be failing");
        }
    }

    @Test
    public void deadlineTask_Date_exceptionThrown() {
        assertThrows(WrongDateFormatException.class,
                () -> new Deadline("project meeting /by December 12 2019 1800"));
    }

    @Test
    public void deadlineTask_Time_exceptionThrown() {
        assertThrows(DateTimeParseException.class,
                () -> new Deadline("project meeting /by 12/08/2020 6pm"));
    }


    @Test
    public void deadlineTask_updateStatus_success()
            throws WrongDateFormatException, WrongTimeFormatException {
        String completed = "[X]";
        Task deadlineTask = new Deadline(
                "return book /by 12/12/2019 18:20")
                .updateStatus(completed);
        assertEquals(
                "[D][X] return book (by: DECEMBER 12 2019 6.20pm)",
                deadlineTask.toString());
    }


    @Test
    public void deadlineTask_updateStatus_storageString()
            throws WrongDateFormatException, WrongTimeFormatException {
        Task deadlineTask = new Deadline("return book /by 12/12/2019 18:20");
        String completed = "[X]";
        assertEquals("[D][X] return book (by: 12-12-2019 18:20)",
                deadlineTask
                        .updateStatus(completed)
                        .getOriginalFormatForStorage());
    }



}
