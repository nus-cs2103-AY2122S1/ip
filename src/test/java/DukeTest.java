import duke.command.Command;
import duke.exception.DukeException;
import duke.exception.DukeIncorrectTaskDescription;
import duke.exception.DukeMissingTaskDescription;
import duke.task.*;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DukeTest {

    @Test
    public void addToDo() {
        String command = "todo read book";
        Task toCompare = Task.createTask(command);
        assertEquals(toCompare.toString(), "[T][ ] read book");
    }

    @Test
    public void addDeadline() {
        String command = "deadline CS2103 iP /by 2021-08-24";
        Task toCompare = Task.createTask(command);
        assertEquals("[D][ ] CS2103 iP (by: Aug 24 2021)", toCompare.toString());
    }

    @Test
    public void addEvent() {
        String command = "event CS2103 tutorial /at 2021-08-25";
        Task toCompare = Task.createTask(command);
        assertEquals("[E][ ] CS2103 tutorial (at: Aug 25 2021)", toCompare.toString());
    }

    @Test
    public void toDoCommand_noDescription_exceptionThrown() {
        String command = "todo";
        String message = "";
        try {
            Task toCompare = Task.createTask(command);
        } catch (DukeException e) {
            message = e.getMessage();
        }
        String expected = new DukeMissingTaskDescription(
                (ToDo) Task.createTask("todo read book"),
                    new IllegalArgumentException()).getMessage();

        assertEquals(expected, message);
    }

    @Test
    public void deadlineCommand_noDescription_exceptionThrown() {
        String command = "deadline";
        String message = "";
        try {
            Task toCompare = Task.createTask(command);
        } catch (DukeException e) {
            message = e.getMessage();
        }
        String expected = new DukeIncorrectTaskDescription(
                (Deadline) Task.createTask("deadline CS2103 iP /by 2021-08-24"),
                new IllegalArgumentException()).getMessage();

        assertEquals(expected, message);
    }

    @Test
    public void eventCommand_noDescription_exceptionThrown() {
        String command = "event";
        String message = "";
        try {
            Task toCompare = Task.createTask(command);
        } catch (DukeException e) {
            message = e.getMessage();
        }
        String expected = new DukeIncorrectTaskDescription(
                (Event) Task.createTask("event CS2103 tutorial /at 2021-08-25"),
                new IllegalArgumentException()).getMessage();

        assertEquals(expected, message);
    }

    @Test
    public void deadlineCommand_wrongFormat_exceptionThrown() {
        String command = "deadline CS2103 iP by 2021-08-24";
        String message = "";

        try {
            Task toCompare = Task.createTask(command);
        } catch (DukeException e) {
            message = e.getMessage();

        }

        String expected = new DukeIncorrectTaskDescription(
                (Deadline) Task.createTask("deadline CS2103 iP /by 2021-08-24"),
                new IllegalArgumentException()).getMessage();

        assertEquals(expected, message);
    }

    @Test
    public void eventCommand_wrongFormat_exceptionThrown() {
        String command = "event CS2103 tutorial at 2021-08-25";
        String message = "";

        try {
            Task toCompare = Task.createTask(command);
        } catch (DukeException e) {
            message = e.getMessage();

        }

        String expected = new DukeIncorrectTaskDescription(
                (Event) Task.createTask("event CS2103 tutorial /at 2021-08-25"),
                new IllegalArgumentException()).getMessage();

        assertEquals(expected, message);
    }
}
