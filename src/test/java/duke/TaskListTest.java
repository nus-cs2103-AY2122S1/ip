package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;

public class TaskListTest {
    @Test
    void constructor_emptyInput_success() {
        assertEquals(new ArrayList<>(), new TaskList().getTasks());
    }

    @Test
    void constructor_listInput_success() {
        ArrayList<String> inputList = new ArrayList<>();
        inputList.add("T,0,description");
        inputList.add("E,1,description,6pm");
        inputList.add("D,0,description,2020-08-25");

        ArrayList<Task> expected = new ArrayList<>();
        expected.add(new Todo("description"));
        Event doneEvent = new Event("description", "6pm");
        doneEvent.markAsDone();
        expected.add(doneEvent);
        expected.add(new Deadline("description", LocalDate.parse("2020-08-25")));

        assertEquals(expected, new TaskList(inputList).getTasks());
    }

    @Test
    void list_emptyTaskList_success() {
        String expected = "Here are the tasks in your list:\n";
        assertEquals(expected, new TaskList().list());
    }

    @Test
    void list_nonEmptyTaskList_success() {
        ArrayList<String> inputList = new ArrayList<>();
        inputList.add("T,0,description");
        inputList.add("E,1,description,6pm");
        inputList.add("D,0,description,2020-08-25");

        String expected = "Here are the tasks in your list:\n"
                + "1.[T][ ] description\n"
                + "2.[E][X] description (at: 6pm)\n"
                + "3.[D][ ] description (by: Aug 25 2020)\n";

        TaskList list = new TaskList(inputList);
        assertEquals(expected, list.list());
    }
}
