package duke;

import org.junit.jupiter.api.Test;

import task.Task;

import java.io.*;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StorageTest {
    Storage testStorage = new Storage("data", "duke.txt");

    /**
     * Tests the Storage load function with an empty list.
     */
    @Test
    public void testEmptyLoad() {
        assertEquals(new ArrayList<Task>(100), testStorage.load());
    }

    /**
     * Tests the handling of finding an entry in the hard disk that is in the incorrect format.
     * @throws IOException If the hard disk is not properly set up and the file cannot be written to.
     */
    @Test
    public void testLoad_faultyEntry() throws IOException {

        File duke = new File("data/duke.txt");
        FileWriter writer = new FileWriter(duke, true);
        writer.write("bruh");
        writer.close();
        
        ByteArrayOutputStream newOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(newOut));
        
        testStorage.load();
        FileWriter rewriter = new FileWriter(duke);
        
        String expected = "  ______________________________________________________________\n" 
                + "  Loading Duke...\n" 
                + "  ______________________________________________________________\n" 
                + "\n" 
                + "  OOPS!!! There was a problem with reading tasks in the hard disk!\n" 
                + "  Check out duke.txt for any erroneous entries or incorrect formatting.\n";

        assertEquals(expected, newOut.toString());
    }
}
