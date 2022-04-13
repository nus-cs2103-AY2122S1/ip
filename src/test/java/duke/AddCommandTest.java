package duke;

import duke.command.AddCommand;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddCommandTest {
    @Test
    public void isExitTest() {
        assertEquals(false, new AddCommand("todo read book", TaskEnum.TODO).isExit());
    }
}
