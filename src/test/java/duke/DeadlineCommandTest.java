package duke;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import duke.commands.DeadlineCommand;

public class DeadlineCommandTest {
    @Test
    public void testNoArgsInput() {
        String input = "deadline   ";
        assertThrows(DukeException.class, () -> new DeadlineCommand(input));
    }

    @Test
    public void testNoDeadlineCmdInput() {
        String input = "deadline blah blah";
        assertThrows(DukeException.class, () -> new DeadlineCommand(input));
    }

    @Test
    public void testMissingDescInput() {
        String input = "deadline /by 2021-02-21";
        assertThrows(DukeException.class, () -> new DeadlineCommand(input));
    }

    @Test
    public void testMissingDeadlineInput() {
        String input = "deadline blah /by";
        assertThrows(DukeException.class, () -> new DeadlineCommand(input));
    }
}
