import bobbybot.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {

    Parser parser = new Parser();
    TaskList tasks = new TaskList(new ArrayList<>());
    Ui ui = new Ui();

    @Test
    public void parse_bye_exitsProgram() throws InvalidArgumentException, TooManyArgumentsException {
        parser.parseCommand("bye", tasks, ui);
        //program should not leave here
        fail();
    }

    //testing print statements
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
    public void parse_find_success() throws InvalidArgumentException, TooManyArgumentsException {
        tasks.createToDo("test description");
        tasks.createToDo("description");
        tasks.createToDo("test find ");
        parser.parseCommand("find test", tasks, ui);
        String expectedString = "Got it. I've added this task:\n" +
                "  [T][ ] test description\n" +
                "Now you have 1 tasks in the list.\n" +
                "Got it. I've added this task:\n" +
                "  [T][ ] description\n" +
                "Now you have 2 tasks in the list.\n" +
                "Got it. I've added this task:\n" +
                "  [T][ ] test find \n" +
                "Now you have 3 tasks in the list.\n" +
                "Here are the tasks you're looking for sir!\n" +
                "1.[T][ ] test description\n" +
                "2.[T][ ] test find";
        assertEquals(expectedString, outputStreamCaptor.toString().trim());
    }
}
