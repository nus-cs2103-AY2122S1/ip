package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UiTest {

    @Test
    void lineProducer() {
        Ui ui = new Ui();
        assertEquals("   ------------------------------------------------------------------" , ui.lineProducer());
    }
}