package duke.command;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class AddCommandTest {
    @Test
    public void isExitTest(){
        AddCommand c = new AddCommand("todo ddd", 1);
        assertFalse(c.isExit());
    }
}
