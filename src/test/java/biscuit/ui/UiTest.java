package biscuit.ui;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

/***
 * Unit test for Ui class.
 */
class UiTest {

    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outputStream));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(System.out);
    }

    @Test
    void showWelcome() {
        Ui ui = new Ui();
        ui.showWelcome();
        String welcomeMessage = "Woof from\n"
                + "████████████████████████████████████████\n"
                + "█▄─▄─▀█▄─▄█─▄▄▄▄█─▄▄▄─█▄─██─▄█▄─▄█─▄─▄─█\n"
                + "██─▄─▀██─██▄▄▄▄─█─███▀██─██─███─████─███\n"
                + "▀▄▄▄▄▀▀▄▄▄▀▄▄▄▄▄▀▄▄▄▄▄▀▀▄▄▄▄▀▀▄▄▄▀▀▄▄▄▀▀\n\n"
                + "      ████████████████████\n"
                + "    ██░░░░░░░░░░░░░░░░░░░░██\n"
                + "  ██░░░░██░░░░░░░░░░░░██░░░░██\n"
                + "  ██░░░░██░░██░░░░██░░██░░░░██\n"
                + "  ██░░████░░░░░░░░░░░░████░░██\n"
                + "    ██  ██░░░░████░░░░██  ██\n"
                + "        ██░░░░░██░░░░░██                  ██\n"
                + "          ██░░░░░░░░██░░████████        ██░░██\n"
                + "        ██░░████████░░░░░░░░░░░░██      ██░░██\n"
                + "      ██░░░░░░░░░░░░░░░░░░░░░░░░░░██  ██░░░░██\n"
                + "      ██░░░░░░░░░░░░░░██░░░░░░░░░░░░██░░░░██\n"
                + "      ██░░░░░░░░░░░░░░██░░░░░░░░░░░░██░░░░██\n"
                + "      ██░░░░░░██░░░░░░██░░░░░░░░░░░░░░░░██\n"
                + "      ██░░░░░░██░░░░░░██░░░░░░░░░░░░░░██\n"
                + "      ██░░░░░░██░░░░░░██░░░░░░░░░░████\n"
                + "████████████████████████████████████████\n"
                + System.lineSeparator()
                + "Woof! I'm Biscuit.\nWhat can I do for you?"
                + System.lineSeparator()
                + "────────────────────────────────────────"
                + System.lineSeparator();
        assertEquals(welcomeMessage, outputStream.toString());
    }

    @Test
    void showLine() {
        Ui ui = new Ui();
        ui.showLine();
        assertEquals("────────────────────────────────────────" + System.lineSeparator(), outputStream.toString());
    }

    @Test
    void showMessage() {
        Ui ui = new Ui();
        String message = "Dummy Message";
        ui.showMessage(message);
        assertEquals(message + System.lineSeparator(), outputStream.toString());
    }

    @Test
    void showError() {
        Ui ui = new Ui();
        String error = "Dummy Error";
        ui.showError(error);
        assertEquals(error + System.lineSeparator(), outputStream.toString());
    }

    @Test
    void readCommand() {
        String command = "Dummy input";
        InputStream inputStream = new ByteArrayInputStream(command.getBytes());
        System.setIn(inputStream);
        Ui ui = new Ui();
        String actualCommand = ui.readCommand();
        assertEquals(command, actualCommand);
    }
}