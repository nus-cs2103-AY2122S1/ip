package sun.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class UiTest {
    @Test
    public void emptyDescriptionTest_success() {
        assertEquals("Please enter a valid event command in the format "
                + "'event <event description> /at ddMMyy HHmm'. (e.g event party /at 120421 1700)",
                    Ui.getCommandFormatError("event"));
    }
}
