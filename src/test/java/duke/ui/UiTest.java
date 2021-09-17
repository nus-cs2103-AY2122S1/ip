package duke.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;


public class UiTest {
    @Test
    public void ioMsgTest_success() {
        assertEquals("IOException", Ui.getIoMsg());
    }

    @Test
    public void unknownInputMsgTest_success() {
        assertEquals("    I'm sorry, but I don't know what that means :-(" , Ui.getUnknownInputMsg());
    }
}
