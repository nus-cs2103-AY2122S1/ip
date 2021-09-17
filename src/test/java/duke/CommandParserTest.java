package duke;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class CommandParserTest {
    @Test
    public void parse_unknownCommands_dukeExceptionThrown() {
        CommandParser testParser = new CommandParser();
        String[] testCommands = new String[] {
            "deadlin x /by 1", "deadlines x /by 2", "deadlinex/by3",
            "even x /at 1", "events x /at 2", "eventx/at3",
        };
        for (String testCommand : testCommands) {
            DukeException exception = assertThrows(DukeException.class, () -> testParser.parse(testCommand));
            assertEquals("☹ OOPS!!! I'm sorry, but I don't know what that means :-(",
                    exception.getMessage());
        }
    }

    @Test
    public void parse_deadline_correctCommandArray() throws DukeException {
        CommandParser testParser = new CommandParser();
        String[] inputs = new String[] {"deadline bruh /by 2020-01-03", "deadline bruh2 /by   hewwo"};
        String[][] results = new String[][] {
            new String[]{"deadline", "bruh", "2020-01-03"},
            new String[]{"deadline", "bruh2", "hewwo"}
        };

        for (int i = 0; i < 2; i++) {
            assertArrayEquals(results[i], testParser.parse(inputs[i]));
        }

    }

    @Test
    public void parse_event_correctCommandArray() throws DukeException {
        CommandParser testParser = new CommandParser();
        String[] inputs = new String[]{"event bruh /at 2020-01-03", "event bruh2 /at   hewwo"};
        String[][] results = new String[][] {
            new String[]{"event", "bruh", "2020-01-03"},
            new String[]{"event", "bruh2", "hewwo"}
        };

        for (int i = 0; i < 2; i++) {
            assertArrayEquals(results[i], testParser.parse(inputs[i]));
        }

    }
}
