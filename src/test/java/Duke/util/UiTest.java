package duke.util;

import duke.exception.FileWritingException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UiTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;
    private final String HORIZONTAL_LINE = "____________________________________________________________";

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    @Test
    public void testPrintMessage(){
        Ui ui = new Ui();
        ui.printMessage("Test123");
        assertEquals(HORIZONTAL_LINE + "\nTest123\n" + HORIZONTAL_LINE + "\n", outContent.toString());
    }

    @Test
    public void testPrintWelcomeMessage(){
        Ui ui = new Ui();
        ui.printWelcomeMessage();
        assertEquals(HORIZONTAL_LINE
                + "\nHello! I'm Duke"
                + "\nWhat can I do for you?\n"
                + HORIZONTAL_LINE
                + "\n", outContent.toString());
    }

    @Test
    public void testPrintGoodbyeMessage(){
        Ui ui = new Ui();
        ui.printGoodByeMessage();
        assertEquals(HORIZONTAL_LINE
                + "\nBye. Hope to see you again soon!\n"
                + HORIZONTAL_LINE
                + "\n", outContent.toString());
    }

    @Test
    public void testPrintErrorMessage(){
        Ui ui = new Ui();
        ui.printErrorMessage(new FileWritingException());
        assertEquals(HORIZONTAL_LINE
                + "\nHi, Duke.util.Duke ran into an error trying to save task to your hard drive. Please try again!\n"
                + HORIZONTAL_LINE
                + "\n", outContent.toString());
    }

    @Test
    public void testPrintHelpMessage(){
        Ui ui = new Ui();
        ui.printHelpMessage();
        String helpString = "Here are the following commands Duke.util.Duke accepts:\n"
                + "todo - adds a todo task, type 'todo' followed by a description\n"
                + "event - adds a event task, type 'todo' followed by a description and event duration\n"
                + "deadline - adds a deadline task, type 'deadline' followed by a description and event due date\n"
                + "list - shows the current task list\n"
                + "done - marks a task in the task list as complete, type 'done' followed by an integer\n"
                + "delete - deletes a task in the task list, type 'delete' followed by an integer\n"
                + "bye - exits the duke chat bot and saves all task in the task list to the hard disk";
        assertEquals(HORIZONTAL_LINE
                + "\n"
                + helpString
                + "\n"
                + HORIZONTAL_LINE
                + "\n", outContent.toString());
    }
}
