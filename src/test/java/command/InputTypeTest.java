package command;

import duke.command.InputType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InputTypeTest {
    @Test
    public void getValueTest() {
        assertEquals(InputType.BYE.getValue(), "bye");
        assertEquals(InputType.TODO.getValue(), "todo");
        assertEquals(InputType.DEADLINE.getValue(), "deadline");
        assertEquals(InputType.EVENT.getValue(), "event");
        assertEquals(InputType.LIST.getValue(), "list");
        assertEquals(InputType.DONE.getValue(), "done");
        assertEquals(InputType.DELETE.getValue(), "delete");
        assertEquals(InputType.UNKNOWN.getValue(), "unknown");
        assertEquals(InputType.FIND.getValue(), "find");
        assertEquals(InputType.HELP.getValue(), "help");
    }
}
