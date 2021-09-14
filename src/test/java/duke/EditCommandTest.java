package duke;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import duke.commands.EditCommand;

public class EditCommandTest {
    @Test
    public void testInvalidEditInput1() {
        String input = "done";
        assertThrows(DukeException.class, () -> new EditCommand(input));
    }

    @Test
    public void testInvalidEditInput2() {
        String input = "done b";
        assertThrows(DukeException.class, () -> new EditCommand(input));
    }

    @Test
    public void testInvalidEditInput3() {
        String input = "  delete    ";
        assertThrows(DukeException.class, () -> new EditCommand(input));
    }

    @Test
    public void testInvalidEditInput4() {
        String input = "      done     ";
        assertThrows(DukeException.class, () -> new EditCommand(input));
    }
}
