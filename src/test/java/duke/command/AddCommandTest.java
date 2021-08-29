package duke.command;

import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

public class AddCommandTest {
    @Test
    public void isExitTest() {
        AddCommand c = new AddCommand("todo ddd", 1);
        assertFalse(c.isExit());
    }
}
