package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class UiTest {

    @Test
    void lineProducer() {
        Ui ui = new Ui();
        assertEquals("   ------------------------------------------------------------------" , ui.lineProducer());
    }
}
