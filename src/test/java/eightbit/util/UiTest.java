package eightbit.util;

import eightbit.EightBitException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class UiTest {

    private Ui ui = new Ui();
    private ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private PrintStream originalOut = System.out;

    @BeforeEach
    void setUpStreams() {
        System.setOut(new PrintStream(outputStream));
    }

    @Test
    void printWithLines() {
        String msg = "Hello World!";
        String expected = "-------------------------------------------------------\n"
                + "Hello World!\n"
                + "-------------------------------------------------------" + System.lineSeparator();

        ui.printWithLines(msg);
        assertEquals(expected, outputStream.toString());
    }

    @Test
    void showWelcome() {
        ui.showWelcome();
        String expected = "-------------------------------------------------------\n"
                + "Hello! I'm 8-Bit!\nWhat can I do for you?\n"
                + "-------------------------------------------------------" + System.lineSeparator();
        assertEquals(expected, outputStream.toString());
    }

    @Test
    void showBye() {
        ui.showBye();
        String expected = "-------------------------------------------------------\n"
                + "Bye. Hope to see you again soon!\n"
                + "-------------------------------------------------------" + System.lineSeparator();
        assertEquals(expected, outputStream.toString());
    }

    @Test
    void printError() {
        EightBitException e = new EightBitException("Some exception");
        ui.printError(e);
        String expected = "-------------------------------------------------------\n"
                + "Some exception\n"
                + "-------------------------------------------------------" + System.lineSeparator();
        assertEquals(expected, outputStream.toString());
    }

    @AfterEach
    void restoreStreams() {
        System.setOut(originalOut);
    }
}
