package duke.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import duke.constant.EditType;
import org.junit.jupiter.api.Test;

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
                + "1.[T][ ] Read book\n"
                + "2.[D][X] Math assignment (by: 21 August 2021)\n"
                + "3.[E][ ] Team meeting (at: 2 September 2021)\n",
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
}
