import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import bobbybot.commands.AddCommand;
import bobbybot.commands.AddContactCommand;
import bobbybot.commands.Command;
import bobbybot.commands.DeleteCommand;
import bobbybot.commands.DoneCommand;
import bobbybot.commands.ExitCommand;
import bobbybot.commands.FindCommand;
import bobbybot.commands.IncorrectCommand;
import bobbybot.commands.ListCommand;
import bobbybot.util.Parser;

public class ParserTest {
    private final Parser parser = new Parser();
    @Test
    public void parse_noArgDeleteCommand_fail() {
        String input = "done";
        Command c = parser.parseCommand(input);
        assertTrue(c instanceof IncorrectCommand);
    }

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
        assertTrue(c instanceof ExitCommand);
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

    @Test
    public void parse_delete_success() {
        String input = "delete 1";
        Command c = parser.parseCommand(input);
        assertTrue(c instanceof DeleteCommand);
    }

    @Test
    public void parse_extraArgDeleteCommand_fail() {
        String input = "delete 1 4";
        Command c = parser.parseCommand(input);
        assertTrue(c instanceof IncorrectCommand);
    }

    @Test
    public void parse_listCommand_success() {
        String input = "list";
        Command c = parser.parseCommand(input);
        assertTrue(c instanceof ListCommand);
    }

    @Test
    public void parse_listCommandExtraArgs_success() {
        String input = "list 2 2";
        Command c = parser.parseCommand(input);
        System.out.println(c.getClass());
        assertTrue(c instanceof ListCommand);
    }

    @Test
    public void parse_listSpellingError_fail() {
        String input = "lists";
        Command c = parser.parseCommand(input);
        assertTrue(c instanceof IncorrectCommand);
    }

    @Test
    public void parse_findCommand_success() {
        String input = "find";
        Command c = parser.parseCommand(input);
        assertTrue(c instanceof FindCommand);
    }

    @Test
    public void parse_findCapitalLettersCommand_success() {
        String input = "FIND";
        Command c = parser.parseCommand(input);
        assertTrue(c instanceof FindCommand);
    }

    @Test
    public void parse_findCommandExtraArgs_success() {
        String input = "find 2 2";
        Command c = parser.parseCommand(input);
        System.out.println(c.getClass());
        assertTrue(c instanceof FindCommand);
    }

    @Test
    public void parse_findSpellingError_fail() {
        String input = "lists";
        Command c = parser.parseCommand(input);
        assertTrue(c instanceof IncorrectCommand);
    }

    @Test
    public void parse_contact_success() {
        String input = "contact Darren p/83821019 e/mokkwd@gmail.com a/rainbow land 32";
        Command c = parser.parseCommand(input);
        assertTrue(c instanceof AddContactCommand);
    }

    @Test
    public void parse_contactWrongArg_fail() {
        String input = "contact something";
        Command c = parser.parseCommand(input);
        assertTrue(c instanceof IncorrectCommand);
    }
    @Test

    public void parse_contactWrongArgFormat_fail() {
        String input = "contact Darren /p83821019 e/mokkwd@gmail.com a/rainbow land 32";
        Command c = parser.parseCommand(input);
        assertTrue(c instanceof IncorrectCommand);
    }
}
