import duke.Ui;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class UiTest {

    private final ByteArrayOutputStream out = new ByteArrayOutputStream();
    private final ByteArrayOutputStream err = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    @Before
    public void setStreams() {
        System.setOut(new PrintStream(out));
        System.setErr(new PrintStream(err));
    }

    @After
    public void restoreInitialStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }
    @Test
    public void greetTest() {
        Ui ui = new Ui();
        ui.greet();
        assertEquals("\t____________________________________________________________\n" +
                        "\tHello! I'm Duke.\n" +
                        "\tWhat can I do for you?\n" +
                        "\t____________________________________________________________\n"
                ,out.toString());
    }

}
