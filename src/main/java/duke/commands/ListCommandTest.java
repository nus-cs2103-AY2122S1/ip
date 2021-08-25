package duke.commands;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class ListCommandTest {

    @Test
    public void testExit() {
        ListCommand lst = new ListCommand();
        assertFalse(lst.isExit());
    }

}
