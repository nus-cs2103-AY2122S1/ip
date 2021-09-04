package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import duke.command.EventCommand;
import duke.data.TaskList;
import duke.data.task.Event;
import duke.storage.Storage;

public class EventCommandTest {

    @Test
    public void executeCreateEventCommand_validInput_responseReturned() {
        TaskList taskList = new TaskList();
        Storage storage = new Storage("tasks.txt");
        String commandDesc = "NEW EVENT";
        LocalDateTime commandDateTime = LocalDateTime.now();
        Event commandTask = new Event(commandDesc, commandDateTime);
        EventCommand command = new EventCommand(commandTask);
        final String expected = "Got it. I've added this task:  "
                + commandTask
                + "Now you have 1 tasks in the list.";
        final String cleanOutput = command.execute(taskList, storage).replaceAll("(\\r|\\n)", "");
        assertEquals(expected, cleanOutput);
    }
}
