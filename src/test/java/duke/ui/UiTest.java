package duke.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UiTest {
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    public void reset() {
        System.setOut(standardOut);
    }

    @Test
    public void print_validFormat_correctString() {
        Ui ui = new Ui();
        ui.print("%d, %s, %.2f", 1, "two", 3.14212322);
        assertEquals("1, two, 3.14", outputStreamCaptor.toString().trim());
    }

    @Test
    public void print_invalidFormat_exceptionThrown() throws Exception {
        Ui ui = new Ui();
        assertThrows(Exception.class, () -> ui.print("%d", "two"));
    }
}
