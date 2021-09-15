package duke.task;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import duke.exceptions.DukeException;

import org.junit.jupiter.api.Test;

public class TodoTaskTest {
    private static final String DELIMITER_SPLIT = " \\| ";
    private static final String IDENTIFIER_DONE = "1";

    @Test
    public void getTaskFromStorageString_validTaskStrings_taskReturned() {
        String[] validTaskStrings = {
            "1 | read book",
            "0 | do home work"
        };

        for (String taskString : validTaskStrings) {
            assertDoesNotThrow(() -> {
                TodoTask.getTaskFromStorageString(taskString, DELIMITER_SPLIT, IDENTIFIER_DONE);
            });
        }
    }

    @Test
    public void getTaskFromStorageString_invalidTaskStrings_exceptionsThrown() {
        String[] invalidTaskStrings = {
                "",
                "read book",
                "do |homework",
                "0 |"
        };

        for (String taskString : invalidTaskStrings) {
            assertThrows(DukeException.class, () -> {
                TodoTask.getTaskFromStorageString(taskString, DELIMITER_SPLIT, IDENTIFIER_DONE);
            });
        }
    }
}
