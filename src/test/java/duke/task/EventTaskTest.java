package duke.task;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import duke.exceptions.DukeException;

import org.junit.jupiter.api.Test;

public class EventTaskTest {

    private static final String DELIMITER_SPLIT = " \\| ";
    private static final String IDENTIFIER_DONE = "1";

    @Test
    public void getTaskFromStorageString_validTaskStrings_taskReturned() {
        String[] validTaskStrings = {
                "0 | return book | 2021-10-15 | 00:00",
                "1 | return book | 2021-10-15 | 18:00"
        };

        for (String taskString : validTaskStrings) {
            assertDoesNotThrow(() -> {
                EventTask.getTaskFromStorageString(taskString, DELIMITER_SPLIT, IDENTIFIER_DONE);
            });
        }
    }

    @Test
    public void getTaskFromStorageString_invalidTaskStrings_exceptionsThrown() {
        String[] invalidTaskStrings = {
                "",
                "return book",
                "0 | return book | 2021-10-15",
                "0 | return book | 2021-18-15 | 25:00",
                "0 | return book | 2021-10-15 | 25:00",
        };

        for (String taskString : invalidTaskStrings) {
            assertThrows(DukeException.class, () -> {
                EventTask.getTaskFromStorageString(taskString, DELIMITER_SPLIT, IDENTIFIER_DONE);
            });
        }
    }

    @Test
    public void getTaskFromCommandString_validCommands_taskReturned() {
        String[] validCommands = {
                "return book /at 2021-10-15",
                "return book /at 2021-10-15 18:00"
        };

        for (String command : validCommands) {
            assertDoesNotThrow(() -> {
                EventTask.getTaskFromCommandString(command);
            });
        }
    }

    @Test
    public void getTaskFromCommandString_invalidCommands_exceptionsThrown() {
        String[] invalidCommands = {
                "",
                "return book",
                "return book /at",
                "return book /at 2021-18-15",
                "return book /at 2021-18-15 18:00",
                "return book /at 2021-10-15 29:00",
                "return book /at 2021-10-15 21:00:00:00"
        };

        for (String command : invalidCommands) {
            assertThrows(DukeException.class, () -> {
                EventTask.getTaskFromCommandString(command);
            });
        }
    }

}
