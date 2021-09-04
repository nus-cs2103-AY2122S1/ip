package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import duke.command.DeadlineCommand;
import duke.data.TaskList;
import duke.data.task.Deadline;
import duke.storage.Storage;

public class DeadlineCommandTest {
    @Test
    public void executeDeadlineCommand_validInput_responseReturned() {
        TaskList taskList = new TaskList();
        Storage storage = new Storage("tasks.txt");
        String commandDesc = "NEW DEADLINE";
        LocalDateTime commandDateTime = LocalDateTime.now();
        Deadline commandTask = new Deadline(commandDesc, commandDateTime);
        DeadlineCommand command = new DeadlineCommand(commandTask);
        final String expected = "Got it. I've added this task:  "
                + commandTask
                + "Now you have 1 tasks in the list.";
        final String cleanOutput = command.execute(taskList, storage).replaceAll("(\\r|\\n)", "");
        assertEquals(expected, cleanOutput);
    }
}
