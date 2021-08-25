package duke.ui;

import duke.constant.EditType;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.TaskList;
import duke.task.ToDo;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class UiTest {
    private Ui ui = new Ui();
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @Test
    public void printTaskListTest(){
        TaskList taskList = new TaskList(List.of(
                new ToDo("Read book"),
                new Deadline("Math assignment", true, "2021-08-21"),
                new Event("Team meeting", "2021-09-02")
        ));
        ui.printTaskList(taskList);
        assertEquals("____________________________________________________________\n"
                + "Here are the tasks in your list:\n"
                + "1.[T][ ] Read book\n"
                + "2.[D][X] Math assignment (by: 21 August 2021)\n"
                + "3.[E][ ] Team meeting (at: 2 September 2021)\n"
                + "____________________________________________________________\n\r\n"
                , outContent.toString());
    }

    @Test
    public void updateUserOnEditedTask_taskDone() {
        ui.updateUserOnEditedTask(new Deadline("Final submission", true, "2021-12-13"),
                5, EditType.DONE
        );
        assertEquals("____________________________________________________________\n"
                + "Nice! I've marked this task as done:\n"
                + "[D][X] Final submission (by: 13 December 2021)\n"
                + "____________________________________________________________\n\r\n",
                outContent.toString());
    }

    @Test
    public void updateUserOnEditedTask_taskDeleted() {
        ui.updateUserOnEditedTask(new Deadline("Final submission", true, "2021-12-13"),
                5, EditType.DELETE
        );
        assertEquals("____________________________________________________________\n"
                + "Got it! I've removed this task from the list:\n"
                + "[D][X] Final submission (by: 13 December 2021)\n"
                + "Now you have 5 tasks in the list.\n"
                + "____________________________________________________________\n\r\n",
                outContent.toString());
    }

    @Test
    public void updateUserOnAddedTaskTest() {
        ui.updateUserOnAddedTask(new Deadline("Final submission", "2021-12-13"), 5);
        assertEquals("____________________________________________________________\n"
                + "Got it. I've added this task:\n"
                + "[D][ ] Final submission (by: 13 December 2021)\n"
                + "Now you have 5 tasks in the list.\n"
                + "____________________________________________________________\n\r\n",
                outContent.toString());
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
    }
}
