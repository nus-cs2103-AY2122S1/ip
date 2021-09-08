import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import bobbybot.commands.AddCommand;
import bobbybot.commands.Command;
import bobbybot.commands.DoneCommand;
import bobbybot.commands.IncorrectCommand;
import bobbybot.util.Parser;
import bobbybot.util.Storage;
import bobbybot.util.TaskList;
import bobbybot.util.Ui;


public class ParserTest {
    private final TaskList tasks = new TaskList(new ArrayList<>());
    private final Ui ui = new Ui();
    private final Storage storage = new Storage("src/test.txt");
    private final Parser parser = new Parser();

    @Test
    public void parse_emptyCommand_fail() {
        String input = "";
        Command c = parser.parseCommand(input);
        assertTrue(c instanceof IncorrectCommand);
    }

    @Test
    public void parse_bye_exitsProgram() {
        String input = "bye";
        Command c = parser.parseCommand(input);
        c.execute(tasks, ui , storage);
        assertTrue(c.isExit());
    }

    @Test
    public void parse_todo_success() {
        String input = "todo something";
        Command c = parser.parseCommand(input);
        assertTrue(c instanceof AddCommand);
    }

    @Test
    public void parse_event_success() {
        String input = "event something /at Sunday";
        Command c = parser.parseCommand(input);
        assertTrue(c instanceof AddCommand);
    }

    @Test
    public void parse_eventCapitalLetter_success() {
        String input = "EVENT something /at Sunday";
        Command c = parser.parseCommand(input);
        assertTrue(c instanceof AddCommand);
    }
    @Test
    public void parse_eventNoDescription_fail() {
        String input = "Event /at 2";
        Command c = parser.parseCommand(input);
        assertTrue(c instanceof IncorrectCommand);
    }

    @Test
    public void parse_eventNoArg_fail() {
        String input = "Event";
        Command c = parser.parseCommand(input);
        assertTrue(c instanceof IncorrectCommand);
    }

    @Test
    public void parse_eventSpellingMistake_fail() {
        String input = "events something /at Sunday";
        Command c = parser.parseCommand(input);
        assertTrue(c instanceof IncorrectCommand);
    }

    @Test
    public void parse_eventBy_fail() {
        String input = "Event something /by 2021";
        Command c = parser.parseCommand(input);
        assertTrue(c instanceof IncorrectCommand);
    }

    @Test
    public void parse_deadline_success() {
        String input = "deadline something /by 01-01-2020 12:00";
        Command c = parser.parseCommand(input);
        assertTrue(c instanceof AddCommand);
    }

    @Test
    public void parse_deadlineAt_fail() {
        String input = "deadline something /at 01-01-2020 12:00";
        Command c = parser.parseCommand(input);
        assertTrue(c instanceof IncorrectCommand);
    }

    @Test
    public void parse_deadlineSpellingMistake_fail() {
        String input = "deadlines something /at 01-01-2020 12:00";
        Command c = parser.parseCommand(input);
        assertTrue(c instanceof IncorrectCommand);
    }

    @Test
    public void parse_deadlineNoArgs_fail() {
        String input = "deadline";
        Command c = parser.parseCommand(input);
        assertTrue(c instanceof IncorrectCommand);
    }

    @Test
    public void parse_deadlineTooManyArgs_fail() {
        String input = "deadline something /by 01-01-2020 12:00 Sunday";
        Command c = parser.parseCommand(input);
        assertTrue(c instanceof IncorrectCommand);
    }

    @Test
    public void parse_done_success() {
        String input = "done 1";
        Command c = parser.parseCommand(input);
        assertTrue(c instanceof DoneCommand);
    }

    @Test
    public void parse_extraArgDoneCommand_fail() {
        String input = "done 1 4";
        Command c = parser.parseCommand(input);
        assertTrue(c instanceof IncorrectCommand);
    }

    @Test
    public void parse_noArgDoneCommand_fail() {
        String input = "done";
        Command c = parser.parseCommand(input);
        assertTrue(c instanceof IncorrectCommand);
    }

}
