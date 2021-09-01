package duke.task;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import duke.exceptions.DukeException;

public class DeadlineTaskTest {

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
                DeadlineTask.getTaskFromStorageString(taskString, DELIMITER_SPLIT, IDENTIFIER_DONE);
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
                DeadlineTask.getTaskFromStorageString(taskString, DELIMITER_SPLIT, IDENTIFIER_DONE);
            });
        }
    }

    @Test
    public void getTaskFromCommandString_validCommands_taskReturned() {
        String[] validCommands = {
                "return book /by 2021-10-15",
                "return book /by 2021-10-15 18:00"
        };

        for (String command : validCommands) {
            assertDoesNotThrow(() -> {
                DeadlineTask.getTaskFromCommandString(command);
            });
        }
    }

    @Test
    public void getTaskFromCommandString_invalidCommands_exceptionsThrown() {
        String[] invalidCommands = {
                "",
                "return book",
                "return book /by",
                "return book /by 2021-18-15",
                "return book /by 2021-18-15 18:00",
                "return book /by 2021-10-15 29:00",
                "return book /by 2021-10-15 21:00:00:00"
        };

        for (String command : invalidCommands) {
            assertThrows(DukeException.class, () -> {
                DeadlineTask.getTaskFromCommandString(command);
            });
        }
    }

}
