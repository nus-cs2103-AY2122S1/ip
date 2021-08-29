package duke.general;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import duke.error.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

public class TasklistTest {
    @Test
    public void dummyTest() {
        assertEquals(2, 2);
    }

    @Test
    public void testAddTask() {
        ToDo t = new ToDo("testing");
        Deadline d = new Deadline("testing", "2020-03-01");
        Event e = new Event("testing", "1 July");

        Tasklist list = new Tasklist(new ArrayList<Task>());
        try {
            list.addTask(TaskType.TODO, new String[] {"T", "testing"});
            list.addTask(TaskType.DEADLINE, new String[] {"D", "testing /by 2020-03-01"});
            list.addTask(TaskType.EVENT, new String[] {"E", "testing /at 1 July"});

            assertEquals(t.toString(), list.get(0).toString());
            assertEquals(d.toString(), list.get(1).toString());
            assertEquals(e.toString(), list.get(2).toString());
        } catch (DukeException error) {
            System.out.println("no this no work");
        }
    }

    @Test
    public void addEmptyTaskInputException() {
        ToDo t = new ToDo("");
        Tasklist list = new Tasklist(new ArrayList<>());
        try {
            list.addTask(TaskType.TODO, new String[] {"T"});
        } catch (DukeException e) {
            assertEquals("Description of task cannot be empty!", e.getMessage());
        }
    }

    @Test
    public void deleteTask() {
        ToDo t = new ToDo("testing");
        Deadline d = new Deadline("testing", "2020-03-01");
        Event e = new Event("testing", "1 July");

        Tasklist list = new Tasklist(new ArrayList<Task>());
        try {
            list.addTask(TaskType.TODO, new String[] {"T", "testing"});
            list.addTask(TaskType.DEADLINE, new String[] {"D", "testing /by 2020-03-01"});
            list.addTask(TaskType.EVENT, new String[] {"E", "testing /at 1 July"});

            list.deleteTask(new String[] {"delete", "1"});

            assertEquals(d.toString(), list.get(0).toString());
        } catch (DukeException error) {
            System.out.println("no this no work");
        }
    }

    @Test
    public void deleteTaskNoIndexException() {
        ToDo t = new ToDo("testing");
        Deadline d = new Deadline("testing", "2020-03-01");
        Event e = new Event("testing", "1 July");

        Tasklist list = new Tasklist(new ArrayList<Task>());
        try {
            list.addTask(TaskType.TODO, new String[] {"T", "testing"});
            list.addTask(TaskType.DEADLINE, new String[] {"D", "testing /by 2020-03-01"});
            list.addTask(TaskType.EVENT, new String[] {"E", "testing /at 1 July"});

            list.deleteTask(new String[] {"delete"});
        } catch (DukeException error) {
            assertEquals("That is an invalid index!", error.getMessage());
        }
    }
}
