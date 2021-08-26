import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.BeforeEach;
import skeltal.Parser;
import org.junit.jupiter.api.Test;
import skeltal.Storage;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SkeltalTest {

    private ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    private PrintStream standardOut = System.out;

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    public void testParser() {
        Parser.response("todo quick");
        assertEquals("---------------------------------------------\n" +
                "Got it. I've added this task\n" +
                "[T][ ] 1. quick\n" +
                "Now you have 1 tasks in the list.\n" +
                "---------------------------------------------", outputStreamCaptor.toString().trim());
    }

    @Test
    public void testLoader() {
        Storage.loadFile();
        assertEquals("Saved tasks have been loaded into the skeltal system!", outputStreamCaptor.toString().trim());
    }

}
