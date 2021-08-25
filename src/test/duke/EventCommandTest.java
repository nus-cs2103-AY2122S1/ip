import duke.command.EventCommand;
import duke.data.TaskList;
import duke.data.task.Event;
import duke.storage.Storage;
import duke.ui.Ui;
import org.junit.jupiter.api.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventCommandTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

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
    public void executeCreateEventCommand(){
        TaskList taskList = new TaskList();
        Ui ui = new Ui();
        Storage storage = new Storage("tasks.txt");
        String commandDesc = "NEW EVENT";
        LocalDateTime commandDateTime = LocalDateTime.now();
        Event commandTask = new Event(commandDesc, commandDateTime);
        EventCommand command = new EventCommand(commandTask);
        final String expected = "__________________________________________________________"
                + "Got it. I've added this task:  "
                + commandTask
                + "Now you have 1 tasks in the list."
                + "__________________________________________________________";
        command.execute(taskList, ui, storage);
        final String cleanOutput = outContent.toString()
                .replaceAll("\n", "").replaceAll("\r", "");
        assertEquals(expected, cleanOutput);
    }
}
