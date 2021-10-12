package duke.command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.Test;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Event;
import duke.task.Task;

public class AddCommandTest {
    @Test
    public void addTask_eventTask_success() {
        Task event = new Event("Test event", LocalDate.parse("1939-09-01"));
        TaskList tasks = new TaskList();

        Command addCommand = new AddCommand(event);
        addCommand.execute(tasks, new Ui(), new Storage("./dummy.txt"));
        assertEquals(tasks.getIndex(0), event);
    }
}
