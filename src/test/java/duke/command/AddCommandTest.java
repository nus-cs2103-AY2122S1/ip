package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.task.Event;
import duke.task.Task;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddCommandTest {
    @Test
    public void addTask_eventTask_success(){
        Task event = new Event("Test event", LocalDate.parse("1939-09-01"));
        TaskList tasks = new TaskList();

        Command addCommand = new AddCommand(event);
        try {
            addCommand.execute(tasks, null, null);
        } catch (NullPointerException e) {
            assertEquals(tasks.getIndex(0), event);
        }
    }
}
