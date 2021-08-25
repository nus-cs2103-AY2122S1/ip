package duke.parser;

import duke.command.AddCommand;
import duke.command.EditCommand;
import duke.command.ExitCommand;
import duke.command.ListCommand;
import duke.constant.EditType;
import duke.constant.TaskType;
import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.List;

import static org.junit.Assert.assertEquals;

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

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    @Test
    public void parseSaveFileErrorTest() {
        exceptionRule.expect(DukeException.class);
        exceptionRule.expectMessage("Your save file is corrupted and has an invalid format.");
        Parser.parseSaveFile(List.of("badly formatted task"));
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
        assertEquals(new AddCommand(TaskType.TODO, "Read book", ""),
                Parser.parseUserInput("todo Read book"));
    }

    @Test
    public void parseUserInput_deadline() {
        assertEquals(new AddCommand(TaskType.DEADLINE, "Math assignment", "2021-08-21"),
                Parser.parseUserInput("deadline Math assignment /by 2021-08-21"));
    }

    @Test
    public void parseUserInput_event() {
        assertEquals(new AddCommand(TaskType.EVENT, "Team meeting", "2021-09-01"),
                Parser.parseUserInput("event Team meeting /at 2021-09-01"));
    }

    @Test
    public void parseUserInput_done() {
        assertEquals(new EditCommand(EditType.DONE, 3), Parser.parseUserInput("done 4"));
    }

    @Test
    public void parseUserInput_delete() {
        assertEquals(new EditCommand(EditType.DELETE, 4), Parser.parseUserInput("delete 5"));
    }

    @Test
    public void parseUserInput_unknownCommand_exceptionThrown() {
        exceptionRule.expect(DukeException.class);
        exceptionRule.expectMessage("Invalid command. List of valid commands include:\n"
                + "list|todo|deadline|event|done|delete|bye");
        Parser.parseUserInput("blah");
    }

    @Test
    public void parseUserInput_illegalUseOfDelimiter_exceptionThrown() {
        exceptionRule.expect(DukeException.class);
        exceptionRule.expectMessage("The description of a task cannot contain this character: |");
        Parser.parseUserInput("todo some|description");
    }

    @Test
    public void parseUserInput_emptyTaskDescription_exceptionThrown() {
        exceptionRule.expect(DukeException.class);
        exceptionRule.expectMessage("The description of a task cannot be empty.\n"
                + "Please input your task in the following manner:\n"
                + "todo|deadline|event <task_description>");
        Parser.parseUserInput("todo");
    }

    @Test
    public void parseUserInput_invalidDeadline_exceptionThrown() {
        exceptionRule.expect(DukeException.class);
        exceptionRule.expectMessage("Invalid format for a deadline task.\n"
                + "Please input your deadline task in the following manner:\n"
                + "deadline <task_description> /by <task_deadline>");
        Parser.parseUserInput("deadline no date provided");
    }

    @Test
    public void parseUserInput_invalidEvent_exceptionThrown() {
        exceptionRule.expect(DukeException.class);
        exceptionRule.expectMessage("Invalid format for an event.\n"
                + "Please input your event in the following manner:\n"
                + "event <event_description> /at <event_date>");
        Parser.parseUserInput("event no date provided");
    }

    @Test
    public void parseUserInput_taskNumber_exceptionThrown() {
        exceptionRule.expect(DukeException.class);
        exceptionRule.expectMessage("Please enter a valid task number.");
        Parser.parseUserInput("done f");
    }
}
