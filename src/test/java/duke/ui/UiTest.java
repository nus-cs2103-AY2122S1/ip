package duke.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import duke.constant.EditType;
import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.TaskList;
import duke.task.ToDo;

public class UiTest {
    private Ui ui = new Ui();

    @Test
    public void formatTaskListTest() {
        TaskList taskList = new TaskList(List.of(
                new ToDo("Read book"),
                new Deadline("Math assignment", true, "2021-08-21"),
                new Event("Team meeting", "2021-09-02")
        ));
        assertEquals("Here are the tasks in your list:\n"
                + "1. [T][ ] Read book\n"
                + "2. [D][X] Math assignment (by: 21 August 2021)\n"
                + "3. [E][ ] Team meeting (at: 2 September 2021)\n",
                ui.formatTaskList(taskList));
    }

    @Test
    public void formatTaskListTest_emptyTaskList() {
        TaskList taskList = new TaskList(List.of());
        assertEquals("You currently have no tasks in your list",
                ui.formatTaskList(taskList));
    }

    @Test
    public void formatEditedTask_taskDone() {
        assertEquals("Nice! I've marked this task as done:\n"
                + "[D][X] Final submission (by: 13 December 2021)",
                ui.formatEditedTask(new Deadline("Final submission", true, "2021-12-13"),
                        5, EditType.DONE));
    }

    @Test
    public void formatEditedTask_taskDeleted() {
        assertEquals("Got it! I've removed this task from the list:\n"
                + "[D][X] Final submission (by: 13 December 2021)\n"
                + "Now you have 5 tasks in the list.",
                ui.formatEditedTask(new Deadline("Final submission", true, "2021-12-13"),
                        5, EditType.DELETE));
    }

    @Test
    public void updateUserOnAddedTaskTest() {
        assertEquals("Got it. I've added this task:\n"
                + "[D][ ] Final submission (by: 13 December 2021)\n"
                + "Now you have 5 tasks in the list.",
                ui.formatAddedTask(new Deadline("Final submission", "2021-12-13"), 5));
    }

    @Test
    public void getHelpMessageForCommand_event() {
        assertEquals("The event command lets you add an event to your task list.\n"
                + "Tasks in the 'event' category will be marked with a 'E'\n"
                + "Usage: event <task description> /at <date in YYYY-MM-DD format>\n"
                + "Example: event Meeting /at 2021-05-22", ui.getHelpMessageForCommand("event"));
    }

    @Test
    public void getHelpMessageForCommand_unknownCommand_exceptionThrown() {
        Exception exception = assertThrows(DukeException.class, () ->
                ui.getHelpMessageForCommand("blah"));
        String expectedMessage = "Invalid command. List of valid commands include:\n"
                + "list | todo | deadline | event | done | delete | find | bye";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }
}
