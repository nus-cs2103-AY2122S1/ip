import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringReader;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import duke.Duke;

public class DukeTest {
    @AfterAll
    public static void fileTeardown() {
        File testTask1 = new File("testInput/duke1.txt");
        File testTask2 = new File("testInput/duke2.txt");
        if (new File("testInput").exists()) {
            testTask1.delete();
            testTask2.delete();
        }
    }

    /**
     * TODO: convert Storage to using passed output stream, or add Commander that redirects user interaction to UI from
     * Storage, instead of converting System.out into mock output stream
     * <p>
     * Fix before testing other I/O stuff
     */
    @Test
    @Order(1)
    public void defaultTaskTest() {
        String input = "list\n"
            + "todo Test1\n"
            + "bye\n";

        String expected = "Hello from\n"
            + " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n"
            + "\n"
            + "Hello! I'm Duke.Duke\n"
            + "What can I do for you?\n"
            + "Got it. I've added this task: \n"
            + "  [T][ ] Test1\n"
            + "Now you have 1 task in the list.\n"
            + "Bye. Hope to see you again soon!\n";

        final PrintStream originalOut = System.out;
        final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        Duke testDuke = new Duke("testInput/duke1.txt");
        testDuke.run(new BufferedReader(new StringReader(input)), new PrintWriter(System.out));
        assertEquals(expected, outContent.toString());
    }

    @Test
    @Order(2)
    public void preexistingTaskTest() {
        String firstInput = "list\n"
            + "todo Test1\n"
            + "bye\n";
        String input = "list\n"
            + "bye\n";
        String expected = "Hello from\n"
            + " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n"
            + "\n"
            + "Hello! I'm Duke.Duke\n"
            + "What can I do for you?\n"
            + "1. [T][ ] Test1\n"
            + "Bye. Hope to see you again soon!\n";

        Duke firstDuke = new Duke("testInput/duke2.txt");
        firstDuke.run(new BufferedReader(new StringReader(firstInput)), new PrintWriter(System.out));

        final PrintStream originalOut = System.out;
        final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));


        Duke testDuke = new Duke("testInput/duke2.txt");
        testDuke.run(new BufferedReader(new StringReader(input)), new PrintWriter(System.out));
        assertEquals(expected, outContent.toString());
    }
}
