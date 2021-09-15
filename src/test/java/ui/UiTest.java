package ui;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UiTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setUpEnvironment() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void restoreEnvironment() {
        System.setOut(originalOut);
    }

    @Test
    public void print_validString_printSuccessfully() {
        //Solution below adapted from from https://stackoverflow.com/a/1119559
        Ui.print("validString");
        assertEquals("\tvalidString\n\t____________________________________________________________\n\n",
                outContent.toString());
    }
}
