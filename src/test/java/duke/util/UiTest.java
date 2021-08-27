package duke.util;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UiTest {
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    private static final String SEPARATOR = "\t-------------------------------------------------------";

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }

    @Test
    void testPrintIntroMessage() {
        Ui ui = new Ui();
        ui.printIntroMessage();
        String expected = String.format("%s\n\t%s\n%s\n",
                SEPARATOR,
                "Hello! I'm Duke, your personal CLI bot! :D\n\t"
                        + "I function as a simple TodoList.\n\t"
                        + "I can keep track of 3 types of tasks:\n\t"
                        + "\t> Todo: To add a new todo task, enter 'todo <task>' e.g. 'todo quiz'.\n\t"
                        + "\t> Deadline: To add a new deadline, enter 'deadline <task> /by <date-time>'.\n\t"
                        + "\t> Event: To add a new event, enter 'event <event-name> /at <date-time>'.\n\t"
                        + "\t  * For deadline and event tasks, you can specify the time "
                        + "by using the format 'YYYY-MM-DD' or 'YYYY-MM-DD hhmm'\n\t\t"
                        + "    where 'hhmm' represents the 24 hour clock.\n\t\t"
                        + "    e.g. event dinner /at 2021-12-26 1830\n\t"
                        + "- To see all your tasks, enter 'list'.\n\t"
                        + "- To delete a task, enter 'delete <task-id>' e.g. 'delete 2'.\n\t"
                        + "- You can also mark tasks as done by typing 'done <task-id>' e.g. 'done 2'.\n\t"
                        + "- Once you are done, just enter 'bye' to quit the program.\n\t"
                        + "Have fun! ^_^",
                SEPARATOR);
        assertEquals(expected, outputStreamCaptor.toString());
    }

    @Test
    void testPrintExitMessage() {
        Ui ui = new Ui();
        ui.printExitMessage();
        String expected = String.format("%s\n\t%s\n%s\n",
                                        SEPARATOR,
                                        "Goodbyeeee! Hope to see you again soon! :>",
                                        SEPARATOR);
        assertEquals(expected,outputStreamCaptor.toString());
    }

    @Test
    void testPrompt() {
        Ui ui = new Ui();
        ui.prompt();
        String expected = String.format("%s\n\t%s\n%s\n",
                SEPARATOR,
                "Enter a command ^_^",
                SEPARATOR);
        assertEquals(expected,outputStreamCaptor.toString());
    }
}