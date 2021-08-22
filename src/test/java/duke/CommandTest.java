package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CommandTest {
    @Test
    public void convertInputTest_list() {
        assertEquals(Command.LIST ,Command.convertInput("list"));
    }

    @Test
    public void convertInputTest_done() {
        assertEquals(Command.DONE ,Command.convertInput("done"));
    }

    @Test
    public void convertInputTest_deadline() {
        assertEquals(Command.DEADLINE ,Command.convertInput("deadline"));
    }

    @Test
    public void convertInputTest_event() {
        assertEquals(Command.EVENT ,Command.convertInput("event"));
    }

    @Test
    public void convertInputTest_exit() {
        assertEquals(Command.EXIT ,Command.convertInput("exit"));
    }

    @Test
    public void convertInputTest_todo() {
        assertEquals(Command.TODO ,Command.convertInput("todo"));
    }

    @Test
    public void convertInputTest_invalid() {
        assertEquals(Command.UNRECOGNISED ,Command.convertInput("asd"));
    }
}
