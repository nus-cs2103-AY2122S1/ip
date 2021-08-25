package duke.ui;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UiTest {

    @Test
    public void uiSpeakTest() {
        // gleaned from https://stackoverflow.com/questions/32241057/how-to-test-a-print-method-in-java-using-junit
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        Ui ui = new Ui();
        ui.speak("ABC");

        String expectedOutput  = "\t_________________________________\r\n" +
                "\t ABC\r\n" +
                "\t_________________________________\r\n";

        // Do the actual assertion.
        assertEquals(expectedOutput, outContent.toString());
    }
}
