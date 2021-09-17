package duke.commands;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import duke.exceptions.DukeException;
import duke.exceptions.DuplicateTaskException;
import duke.exceptions.InvalidTimeStampException;
import duke.tasks.Event;
import duke.utils.TaskList;

public class AddEventCommandTest {

    @Test
    public void testInvalidYear() {
        assertThrows(
            InvalidTimeStampException.class,
            () -> new AddEventCommand(new String[] {"PLACEHOLDER_EVENT_DETAILS", "1/2/21 2021"})
        );
    }

    @Test
    public void testInvalidMonth() {
        assertThrows(
            InvalidTimeStampException.class,
            () -> new AddEventCommand(new String[] {"PLACEHOLDER_EVENT_DETAILS", "1/13/2021 2021"})
        );
    }

    @Test
    public void testInvalidDay() {
        assertThrows(
            InvalidTimeStampException.class,
            () -> new AddEventCommand(new String[] {"PLACEHOLDER_EVENT_DETAILS", "32/4/2021 2021"})
        );
    }

    @Test
    public void testInvalidHour() {
        assertThrows(
            InvalidTimeStampException.class,
            () -> new AddEventCommand(new String[] {"PLACEHOLDER_EVENT_DETAILS", "32/4/2021 2421"})
        );
    }

    @Test
    public void testInvalidMinute() {
        assertThrows(
            InvalidTimeStampException.class,
            () -> new AddEventCommand(new String[] {"PLACEHOLDER_EVENT_DETAILS", "32/4/2021 2460"})
        );
    }

    @Test
    public void testAddDuplicateEvent() throws DukeException {
        TaskList taskList = new TaskList();
        taskList.addTask(new Event("Placeholder", "10/9/2021 2141"));

        AddEventCommand addEventCommand = new AddEventCommand(new String[]{"Placeholder", "10/9/2021 2141"});
        assertThrows(
                DuplicateTaskException.class,
                () -> addEventCommand.execute(taskList, null, null)
        );
    }
}
