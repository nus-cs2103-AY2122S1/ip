package duke;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DukeTest {
    
    @Test
    public void testBye() {
        
        ByteArrayInputStream newIn = new ByteArrayInputStream("bye".getBytes());
        ByteArrayOutputStream newOut = new ByteArrayOutputStream();
        
        System.setIn(newIn);
        System.setOut(new PrintStream(newOut));
        
        Duke.main(new String[0]);
        
        String expected = "  ______________________________________________________________\n"
                + "  Loading Duke...\n"
                + "  ______________________________________________________________\n"
                + "\n" 
                + "  ______________________________________________________________\n"
                + "  Hello! I'm Duck.\n"
                + "  What's up?\n"
                + "  ______________________________________________________________\n"
                + "\n"
                + ">   ______________________________________________________________\n"
                + "  See you next time!\n"
                + "  ______________________________________________________________\n"
                + "\n";
        
        assertEquals(expected, newOut.toString());
    }

    @Test
    public void testEmptyList() {

        ByteArrayInputStream newIn = new ByteArrayInputStream("list\nbye".getBytes());
        ByteArrayOutputStream newOut = new ByteArrayOutputStream();

        System.setIn(newIn);
        System.setOut(new PrintStream(newOut));

        Duke.main(new String[0]);

        String expected = "  ______________________________________________________________\n"
                + "  Loading Duke...\n"
                + "  ______________________________________________________________\n"
                + "\n"
                + "  ______________________________________________________________\n"
                + "  Hello! I'm Duck.\n"
                + "  What's up?\n"
                + "  ______________________________________________________________\n"
                + "\n"
                + ">   ______________________________________________________________\n"
                + "  Here are the tasks in your list:\n"
                + "  ______________________________________________________________\n"
                + "\n"
                + ">   ______________________________________________________________\n"
                + "  See you next time!\n"
                + "  ______________________________________________________________\n"
                + "\n";

        assertEquals(expected, newOut.toString());
    }

    @Test
    public void testAddDeadline_invalidDesc_success() {
        
        ByteArrayInputStream newIn = new ByteArrayInputStream("deadline\nbye".getBytes());
        ByteArrayOutputStream newOut = new ByteArrayOutputStream();

        System.setIn(newIn);
        System.setOut(new PrintStream(newOut));

        Duke.main(new String[0]);

        String expected = "  ______________________________________________________________\n"
                + "  Loading Duke...\n"
                + "  ______________________________________________________________\n"
                + "\n"
                + "  ______________________________________________________________\n"
                + "  Hello! I'm Duck.\n"
                + "  What's up?\n"
                + "  ______________________________________________________________\n"
                + "\n"
                + ">   ______________________________________________________________\n"
                + "  OOPS!!! The description of a deadline cannot be empty!\n"
                + "  ______________________________________________________________\n"
                + "\n"
                + ">   ______________________________________________________________\n"
                + "  See you next time!\n"
                + "  ______________________________________________________________\n"
                + "\n";

        assertEquals(expected, newOut.toString());
    }
}
