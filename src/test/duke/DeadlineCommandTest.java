import duke.command.DeadlineCommand;
import duke.data.TaskList;
import duke.data.task.Deadline;
import duke.storage.Storage;
import duke.ui.Ui;
import org.junit.jupiter.api.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineCommandTest {
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
    public void executeCreateDeadlineCommand(){
        TaskList taskList = new TaskList();
        Ui ui = new Ui();
        Storage storage = new Storage("tasks.txt");
        String commandDesc = "NEW DEADLINE";
        LocalDateTime commandDateTime = LocalDateTime.now();
        Deadline commandTask = new Deadline(commandDesc, commandDateTime);
        DeadlineCommand command = new DeadlineCommand(commandTask);
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
