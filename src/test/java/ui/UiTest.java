package ui;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import stub.message.MessageStub;

public class UiTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    @Test
    public void testShowWelcome() {
        new Ui().showWelcome();

        assertEquals("\t_________________________________________________\n"
                        + "\tHello! I'm Duke, what shall we do today? ٩(｡•́‿•̀｡)۶\n"
                        + "\t_________________________________________________\n",
                outContent.toString()
        );
    }

    @Test
    public void testShowGoodbye() {
        new Ui().showGoodbye();

        assertEquals("\t_________________________________________________\n"
                        + "\tBye, see you again ヾ(=´･∀･｀=)\n"
                        + "\t_________________________________________________\n",
                outContent.toString()
        );
    }

    @Test
    public void testShowMessage() {
        new Ui().showMessage(new MessageStub());

        assertEquals("\t_________________________________________________\n"
                        + "\thello :)\n"
                        + "\t_________________________________________________\n",
                outContent.toString()
        );
    }

    @Test
    public void testReadInputMessage() {
        String inputMessage = "how are you";
        System.setIn(new ByteArrayInputStream(inputMessage.getBytes()));
        String inputReadByUi = new Ui().readInputMessage();

        assertEquals(inputMessage, inputReadByUi);
        System.setIn(System.in);
    }
}
