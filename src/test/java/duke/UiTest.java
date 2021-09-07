package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class UiTest {
    @Test
    void byeMessage() {
        Ui ui = new Ui();
        assertEquals("Bye! Please visit me again!", ui.byeMessage());
    }
}
