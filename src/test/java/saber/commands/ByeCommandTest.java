package saber.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class ByeCommandTest {
    private static final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private static final PrintStream originalOut = System.out;

    @BeforeAll
    public static void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterAll
    public static void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    public void isExitTest() {
        SaberCommand command = new ByeCommand();
        boolean isExit = command.isExit();
        assertEquals(true, isExit);
    }

    @Test
    public void showSuccessTest() {
        String successMessage = "      Am I ... no longer needed, Master?\n"
                + "      I understand. I shall excuse myself.\n";
        System.out.println(successMessage);
        assertEquals(successMessage + "\n", outContent.toString());
    }
}
