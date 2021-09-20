package duke.task;

import duke.DukeException;
import duke.Parser;
import duke.Task.Task;
import duke.Task.TaskList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {
    //method of testing System.out adapted from https://www.baeldung.com/java-testing-system-out-println
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }
    @Test
    public void testEventWithoutDeadline(){

        Parser.handleInput("event", new TaskList());
        String actual = outputStreamCaptor.toString().replaceAll("\n", "").replaceAll("\r", "");
        assertEquals("Error: You need to specify at using /at !" +
                " ----------------------------------", actual);


    }
    @Test
    public void testTodoCreation() {
        try{
            Task test = Parser.handleTaskInput("todo eat steak");
            assertEquals("[T][ ]  eat steak",test.toString());

        } catch(DukeException e) {
            fail("No exception thrown");
        }


    }
}
