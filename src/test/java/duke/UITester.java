package duke;
import duke.task.Task;
import org.junit.After;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

public class UITester {
    private final ByteArrayOutputStream out = new ByteArrayOutputStream();
    private final ByteArrayOutputStream err = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    @BeforeEach
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
    public void testBye() {
        Ui ui = new Ui();
        ui.sayBye();
        assertEquals("Don'twakemeupagain", out.toString().replaceAll("\\s+",""));
    }

    @Test
    public void testAddTask() {
        try {
            Ui ui = new Ui();
            ui.taskAdded(Task.makeTask("todo", "get a life"), 2);
            assertEquals("    ____________________________________________________________\n" +
                    "     Got it. I've added this task:\n" +
                    "     [T][ ] get a life\n" +
                    "     Now you have 2 tasks in the list.\n" +
                    "    ____________________________________________________________" +
                    "\n\n", out.toString());
        } catch (DukeException e) {
            assertTrue(false);
            System.out.println("Error thrown");
        }
    }
}
