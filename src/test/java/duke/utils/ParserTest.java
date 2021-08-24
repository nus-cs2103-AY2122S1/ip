package duke.utils;

import duke.Duke;
import duke.commands.AddDeadlineCommand;
import duke.commands.AddEventCommand;
import duke.commands.AddTodoCommand;
import duke.commands.Command;
import duke.exceptions.DukeException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class ParserTest {

    private static String[] DEADLINE_KEYWORDS = new String[] {"deadline", "/by"};

    @Test
    public void testParseDeadlineInput() throws DukeException {
        String command = "deadline PLACEHOLDER /by 21/8/2021 1800";
        Command expected = new AddDeadlineCommand(new String[] {"PLACEHOLDER", "24/8/2021 1800"});
        Command actual = Parser.parseNext(command);
        assertEquals(
                expected.toString(),
                actual.toString()
        );
    }

    @Test
    public void testParseTodoInput() throws DukeException {
        String command = "todo PLACEHOLDER";
        Command expected = new AddTodoCommand(new String[] {"PLACEHOLDER"});
        Command actual = Parser.parseNext(command);
        assertEquals(
                expected.toString(),
                actual.toString()
        );
    }

    @Test
    public void testParseEventInput() throws DukeException {
        String command = "event PLACEHOLDER /at 24/12/2022 1859";
        Command expected = new AddEventCommand(new String[] {"PLACEHOLDER", "24/8/2021 1800"});
        Command actual = Parser.parseNext(command);
        assertEquals(
                expected.toString(),
                actual.toString()
        );
    }

    @Test
    public void testParseLongDetailsInput() throws DukeException {
        String command = "event PLACEHOLDER SENTENCE IS VERY LONG /at 24/8/2021 1800";
        Command expected = new AddEventCommand(new String[] {"PLACEHOLDER SENTENCE IS VERY LONG", "24/8/2021 1800"});
        Command actual = Parser.parseNext(command);
        assertEquals(
                expected.toString(),
                actual.toString()
        );
        assertEquals(
                expected.toString(),
                actual.toString()
        );
    }
}
