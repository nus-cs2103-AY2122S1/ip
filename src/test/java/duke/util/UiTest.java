package duke.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UiTest {
    // @author Jonathan Cook
    // Reused from https://www.baeldung.com/java-testing-system-out-println
    // with minor modifications
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
    void testPrintWelcomeMessage() {
        Ui ui = new Ui();
        ui.printWelcomeMessage();
        assertEquals("--------------------------------------------------------------------------------\n"
                + " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n"
                + "\n"
                + "Hello! I'm Duke :)\n"
                + "What can I do for you? (Type 'help' to see what I can do!)\n"
                + "--------------------------------------------------------------------------------",
                outputStreamCaptor.toString().trim());
    }

    @Test
    void testPrintGoodbyeMessage() {
        Ui ui = new Ui();
        ui.printGoodbyeMessage();
        assertEquals("Bye :< Hope to see you again soon!", outputStreamCaptor.toString().trim());
    }

    @Test
    void testPrintPrompt() {
        Ui ui = new Ui();
        ui.printPrompt();
        assertEquals("Enter Command:", outputStreamCaptor.toString().trim());
    }

    @Test
    void testPrintDivider() {
        Ui ui = new Ui();
        ui.printDivider();
        assertEquals("--------------------------------------------------------------------------------",
                outputStreamCaptor.toString().trim());
    }

    @Test
    void formatPrint() {
        Ui ui = new Ui();
        ui.formatPrint("test");
        assertEquals("--------------------------------------------------------------------------------\n"
                + "test\n"
                + "--------------------------------------------------------------------------------",
                outputStreamCaptor.toString().trim());

    }

    @Test
    void testPrintHelp() {
        Ui ui = new Ui();
        assertEquals("Here is the list of my available commands!\n"
                + "1. todo [description] - Adds a ToDo task to task list\n"
                + "2. deadline [description] /by [date] [time] - Adds a Deadline to task list.\n"
                + "3. event [description] /at [date] [time]-[time] - Adds a Event to task list\n"
                + "4. filter [date] - Filters out list of task on this date\n"
                + "Date formats: dd/mm/yyyy, dd-mm-yyyy, yyyy-mm-dd\n"
                + "Time format: HHmm, HH:mm\n"
                + "5. list - Display list of items you have added\n"
                + "6. done [index of completed task] - Marks specified tasks as completed\n"
                + "7. delete [index of task to be deleted] - Deletes specified task\n"
                + "8. find [keyword to search for] - Finds tasks by specific keyword\n"
                + "9. bye - End the program",
                ui.getHelp());
    }
}
