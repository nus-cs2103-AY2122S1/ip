package duke.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import duke.command.AddCommand;
import duke.command.EditCommand;
import duke.command.ExitCommand;
import duke.command.HelpCommand;
import duke.command.ListCommand;
import duke.constant.EditType;
import duke.constant.TaskType;
import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

public class ParserTest {
    @Test
    public void parseSaveFileTest() {
        assertEquals(List.<Task>of(
                new Deadline("Math assignment", true, "2021-08-21"),
                new Event("Team meeting", "2021-09-01"),
                new ToDo("Read book")),
                Parser.parseSaveFile(List.of(
                        "D|1|Math assignment|2021-08-21",
                        "E|0|Team meeting|2021-09-01",
                        "T|0|Read book")));
    }

    @Test
    public void parseSaveFileErrorTest() {
        Exception exception = assertThrows(DukeException.class, () ->
            Parser.parseSaveFile(List.of("badly formatted task")));
        String expectedMessage = "Your save file is corrupted and has an invalid format.";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void parseUserInput_list() {
        assertEquals(new ListCommand(), Parser.parseUserInput("list"));
    }

    @Test
    public void parseUserInput_bye() {
        assertEquals(new ExitCommand(), Parser.parseUserInput("bye"));
    }

    @Test
    public void parseUserInput_todo() {
        assertEquals(new AddCommand(TaskType.TODO, "todo Read book"),
                Parser.parseUserInput("todo Read book"));
    }

    @Test
    public void parseUserInput_deadline() {
        assertEquals(new AddCommand(TaskType.DEADLINE, "deadline Math assignment /by 2021-08-21"),
                Parser.parseUserInput("deadline Math assignment /by 2021-08-21"));
    }

    @Test
    public void parseUserInput_event() {
        assertEquals(new AddCommand(TaskType.EVENT, "event Team meeting /at 2021-09-01"),
                Parser.parseUserInput("event Team meeting /at 2021-09-01"));
    }

    @Test
    public void parseUserInput_done() {
        assertEquals(new EditCommand(EditType.DONE, "done 4"), Parser.parseUserInput("done 4"));
    }

    @Test
    public void parseUserInput_delete() {
        assertEquals(new EditCommand(EditType.DELETE, "delete 5"), Parser.parseUserInput("delete 5"));
    }

    @Test
    public void parseUserInput_help() {
        assertEquals(new HelpCommand("help"), Parser.parseUserInput("help"));
    }

    @Test
    public void parseUserInput_helpDeadline() {
        assertEquals(new HelpCommand("help deadline"), Parser.parseUserInput("help deadline"));
    }

    @Test
    public void parseUserInput_emptyInput_exceptionThrown() {
        Exception exception = assertThrows(DukeException.class, () ->
                Parser.parseUserInput(""));
        String expectedMessage = "Please tell Duke what to do.";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void parseUserInput_unknownCommand_exceptionThrown() {
        Exception exception = assertThrows(DukeException.class, () ->
            Parser.parseUserInput("blah"));
        String expectedMessage = "Invalid command. List of valid commands include:\n"
                + "help | list | todo | deadline | event | done | delete | find | bye";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }
}
