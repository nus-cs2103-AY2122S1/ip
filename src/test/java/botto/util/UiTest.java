package botto.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UiTest {
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    private Dialog ui;

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
        ui = new Dialog();
    }

    @Test
    public void dummyTest() {
        System.out.println("Hello Baeldung Readers!!");
        assertEquals("Hello Baeldung Readers!!", outputStreamCaptor.toString().trim());
    }


    @Test
    public void showWelcome_noInput_success() {
        ui.showWelcome();

        String expected = "    ------------------------------\n"
                + "    Hello! I'm Botto.\n"
                + "    What can I do for you?\n"
                + "    ------------------------------\n";

        assertEquals(expected, outputStreamCaptor.toString());
    }

    @Test
    public void sayGoodBye_noInput_success() {
        ui.sayGoodBye();

        String expected = "    Bye. Hope to see you again soon!\n";
        assertEquals(expected, outputStreamCaptor.toString());
    }

    @Test
    public void showError_randomInput_success() {
        ui.showError("I don't understand the command");

        assertEquals("    I don't understand the command\n", outputStreamCaptor.toString());
    }


    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }
}
