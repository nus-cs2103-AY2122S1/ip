import Duke.Duke;
import Duke.Storage;
import Duke.TaskList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.*;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

public class DukeTest {
    @TempDir
    Path tempPath;

    /**
     * TODO: convert Storage to using passed output stream, or add Commander that redirects user interaction to UI from
     * Storage, instead of converting System.out into mock output stream
     *
     * Fix before testing other I/O stuff
     */
    @Test
    public void PreexistingTaskTest() {
        String input = "list\n"
                       + "bye\n";
        String expected = "Hello from\n" +
                " ____        _        \n" +
                "|  _ \\ _   _| | _____ \n" +
                "| | | | | | | |/ / _ \\\n" +
                "| |_| | |_| |   <  __/\n" +
                "|____/ \\__,_|_|\\_\\___|\n" +
                "\n" +
                "Hello! I'm Duke.Duke\n" +
                "What can I do for you?\n" +
                "1. [T][ ] Test1\n" +
                "Bye. Hope to see you again soon!\n";

        final PrintStream originalOut = System.out;
        final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        Duke testDuke = new Duke("testInput/duke.txt");
        testDuke.run(new BufferedReader(new StringReader(input)), new PrintWriter(System.out));
        assertEquals(expected, outContent.toString());
    }
}