package duke.command;

import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

public class AddCommandTest {
    @Test
    public void isExitTest() {
        AddCommand c = new AddCommand("todo ddd", Operation.TODO);
        assertFalse(c.isExit());
        c = new AddCommand("deadline xx /by tomorrow", Operation.DEADLINE);
        assertFalse(c.isExit());
    }
}
