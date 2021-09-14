package duke;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import duke.commands.EventCommand;

public class EventCommandTest {
    @Test
    public void testNoArgsInput() {
        String input = "event   ";
        assertThrows(DukeException.class, () -> new EventCommand(input));
    }

    @Test
    public void testNoDeadlineCmdInput() {
        String input = "event blah blah";
        assertThrows(DukeException.class, () -> new EventCommand(input));
    }

    @Test
    public void testMissingDescInput() {
        String input = "event /at 6pm";
        assertThrows(DukeException.class, () -> new EventCommand(input));
    }

    @Test
    public void testMissingDeadlineInput() {
        String input = "event blah /at";
        assertThrows(DukeException.class, () -> new EventCommand(input));
    }
}
