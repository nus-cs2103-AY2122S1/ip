package duke.commands;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import duke.exceptions.DukeException;
import duke.exceptions.DuplicateTaskException;
import duke.exceptions.InvalidTimeStampException;
import duke.tasks.Deadline;
import duke.utils.TaskList;


public class AddDeadlineCommandTest {

    @Test
    public void testInvalidYear() {
        assertThrows(
                InvalidTimeStampException.class,
                () -> new AddDeadlineCommand(new String[] {"PLACEHOLDER_EVENT_DETAILS", "1/2/21 2021"})
        );
    }

    @Test
    public void testInvalidMonth() {
        assertThrows(
                InvalidTimeStampException.class,
                () -> new AddDeadlineCommand(new String[] {"PLACEHOLDER_EVENT_DETAILS", "1/13/2021 2021"})
        );
    }

    @Test
    public void testInvalidDay() {
        assertThrows(
                InvalidTimeStampException.class,
                () -> new AddDeadlineCommand(new String[] {"PLACEHOLDER_EVENT_DETAILS", "32/4/2021 2021"})
        );
    }

    @Test
    public void testInvalidHour() {
        assertThrows(
                InvalidTimeStampException.class,
                () -> new AddDeadlineCommand(new String[] {"PLACEHOLDER_EVENT_DETAILS", "32/4/2021 2421"})
        );
    }

    @Test
    public void testInvalidMinute() {
        assertThrows(
                InvalidTimeStampException.class,
                () -> new AddDeadlineCommand(new String[] {"PLACEHOLDER_EVENT_DETAILS", "32/4/2021 2460"})
        );
    }

    @Test
    public void testAddDuplicateDeadline() throws DukeException {
        TaskList taskList = new TaskList();
        taskList.addTask(new Deadline("Placeholder", "10/9/2021 2141"));

        AddDeadlineCommand addDeadlineCommand = new AddDeadlineCommand(new String[]{"Placeholder", "10/9/2021 2141"});
        assertThrows(
                DuplicateTaskException.class,
                () -> addDeadlineCommand.execute(taskList, null, null)
        );
    }
}
