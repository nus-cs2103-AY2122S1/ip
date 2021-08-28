package utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class UiTest {
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    private Ui ui;

    @BeforeEach
    public void setUp() {
        ui = new Ui();
    }

    @Test
    public void printWelcomeMessage_welcomeMessagePrinted(){

        System.setOut(new PrintStream(outputStreamCaptor));

        ui.printWelcomeMessage();

        String expected = "\t____________________________\n" +
                "\t " + "Hello! I'm Mr House" + "\n" +
                "\t____________________________\n";

        Assertions.assertEquals(expected + "\r\n", outputStreamCaptor.toString()
                );

        System.setOut(standardOut);
    }

    @Test
    public void printResponseTest_singleLineString_formattedMessagePrinted() {
        System.setOut(new PrintStream(outputStreamCaptor));

        ui.printResponse("Added new Task");

        String expected = "\t____________________________\n" +
                "\t " + "Added new Task" + "\n" +
                "\t____________________________\n";

        Assertions.assertEquals(expected + "\r\n", outputStreamCaptor.toString()
        );

        System.setOut(standardOut);
    }
    
}
