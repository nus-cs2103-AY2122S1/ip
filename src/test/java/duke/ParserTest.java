package duke;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import duke.exception.InvalidFormatException;
import duke.exception.UnknownCommandException;
import duke.util.Parser;

public class ParserTest {
    @Test
    void parse_validCommands_success() {
        String[] commands = {
            "list",
            "done 1",
            "todo something",
            "deadline thing /by 2021-08-25",
            "event somewhere /at 6pm",
            "delete 1",
            "find something"
        };

        for (String command : commands) {
            assertDoesNotThrow(() -> Parser.parse(command));
        }
    }

    @Test
    void parse_unknownCommands_exceptionThrown() {
        String[] commands = {
            "done x",
            "blah",
            "invalid command",
            "delete a"
        };

        for (String command : commands) {
            assertThrows(UnknownCommandException.class, () -> Parser.parse(command));
        }
    }

    @Test
    void getIndexFromCommand_validCommands_success() {
        String[] commands = {
            "delete 2",
            "done 100",
            "done -1"
        };
        int[] expected = {2, 100, -1};

        for (String command : commands) {
            assertDoesNotThrow(() -> Parser.getIndexFromCommand(command));
        }

        for (int i = 0; i < commands.length; i++) {
            try {
                assertEquals(expected[i], Parser.getIndexFromCommand(commands[i]));
            } catch (InvalidFormatException ignored) {
                // Empty. Already asserted that no error is thrown.
            }
        }
    }

    @Test
    void getIndexFromCommand_nonIntegers_exceptionThrown() {
        String[] commands = {
            "delete x",
            "delete 1.1",
            "done y",
            "done 0.0"
        };

        for (String command : commands) {
            assertThrows(InvalidFormatException.class, () -> Parser.getIndexFromCommand(command));
        }
    }

    @Test
    void validateRegexAndMatch_validInputTodo_success() {
        String command = "todo some description";
        String regex = "^todo (.+)";
        String validFormat = "todo {description}";
        String[] expected = {"todo some description", "some description"};

        assertDoesNotThrow(() -> Parser.validateRegexAndMatch(command, regex, validFormat));
        try {
            String[] matches = Parser.validateRegexAndMatch(command, regex, validFormat);
            for (int i = 0; i < expected.length; i++) {
                assertEquals(expected[i], matches[i]);
            }
        } catch (InvalidFormatException ignored) {
            // Empty. Already asserted that no error is thrown.
        }
    }

    @Test
    void validateRegexAndMatch_validInputDeadline_success() {
        String command = "deadline something /by 2020-08-25";
        String regex = "^deadline (.+) /by (.+)";
        String validFormat = "deadline {description} /by {date}";
        String[] expected = {"deadline something /by 2020-08-25", "something", "2020-08-25"};

        assertDoesNotThrow(() -> Parser.validateRegexAndMatch(command, regex, validFormat));
        try {
            String[] matches = Parser.validateRegexAndMatch(command, regex, validFormat);
            for (int i = 0; i < expected.length; i++) {
                assertEquals(expected[i], matches[i]);
            }
        } catch (InvalidFormatException ignored) {
            // Empty. Already asserted that no error is thrown.
        }
    }

    @Test
    void validateRegexAndMatch_validInputEvent_success() {
        String command = "event something /at sometime";
        String regex = "^event (.+) /at (.+)";
        String validFormat = "event {description} /at {time}";
        String[] expected = {"event something /at sometime", "something", "sometime"};

        assertDoesNotThrow(() -> Parser.validateRegexAndMatch(command, regex, validFormat));
        try {
            String[] matches = Parser.validateRegexAndMatch(command, regex, validFormat);
            for (int i = 0; i < expected.length; i++) {
                assertEquals(expected[i], matches[i]);
            }
        } catch (InvalidFormatException ignored) {
            // Empty. Already asserted that no error is thrown.
        }
    }

    @Test
    void validateRegexAndMatch_validInputFind_success() {
        String command = "find something";
        String regex = "^find (.+)";
        String validFormat = "find {search term}";
        String[] expected = {"find something", "something"};

        assertDoesNotThrow(() -> Parser.validateRegexAndMatch(command, regex, validFormat));
        try {
            String[] matches = Parser.validateRegexAndMatch(command, regex, validFormat);
            for (int i = 0; i < expected.length; i++) {
                assertEquals(expected[i], matches[i]);
            }
        } catch (InvalidFormatException ignored) {
            // Empty. Already asserted that no error is thrown.
        }
    }
}
