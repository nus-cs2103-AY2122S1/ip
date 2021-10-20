import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.Test;

import duke.Ui;

public class UiTest {

    private final ByteArrayOutputStream out = new ByteArrayOutputStream();
    private final ByteArrayOutputStream err = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    @Test
    public void greetTest() {
        Ui ui = new Ui();
        assertEquals("__________________________________________\n"
                + "Hello! I'm Duke.\n"
                + "What can I do for you?\n"
                + "__________________________________________\n", ui.greet());
    }

}
