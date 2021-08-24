package duke.test;

import duke.Events;
import duke.Task;
import duke.TaskList;
import duke.ToDos;
import duke.exception.DukeException;
import duke.exception.OutOfBoundException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;

public class TaskListTest {
    @Test
    public void printListTest() {
        ToDos task1 = new ToDos("shower");
        Events task2 = new Events("assignment", "2359hrs");
        ArrayList<Task> l = new ArrayList<>();
        l.add(task1);
        l.add(task2);
        TaskList list = new TaskList(l);
        assertEquals("1. [T][ ]shower\n"
                        + "2. [E][ ]assignment(at:2359hrs)\n", list.printList());
    }

    @Test
    public void addTaskTest() throws DukeException {
        TaskList list = new TaskList(new ArrayList<>());
        Task t = list.addTask("todo shower");
        assertEquals("[T][ ]shower", t.toString());
    }

    @Test
    public void addTaskTest2() {
        try {
            TaskList list = new TaskList(new ArrayList<>());
            Task t = list.addTask("deadline hw");
            fail();
        } catch (DukeException e) {
            assertEquals("☹ OOPS!!! I'm sorry, but I don't know what that means :-( " +
                            "Please key in a valid task!", e.getMessage());
        }
    }

    @Test
    public void deleteTaskTest() throws OutOfBoundException {
        ToDos task1 = new ToDos("shower");
        ArrayList<Task> l = new ArrayList<>();
        l.add(task1);
        TaskList list = new TaskList(l);
        Task t = list.deleteTask("1");
        assertEquals("[T][ ]shower", t.toString());
    }

    @Test
    public void deleteTaskTest2() {
        try {
            ToDos task1 = new ToDos("shower");
            ArrayList<Task> l = new ArrayList<>();
            l.add(task1);
            TaskList list = new TaskList(l);
            list.deleteTask("2");
            fail();
        } catch (DukeException e) {
            assertEquals("Task does not exist. " +
                    "Please send a correct task number ><", e.getMessage());
        }
    }

    @Test
    public void markAsDoneTest() throws OutOfBoundException {
        ToDos task1 = new ToDos("shower");
        ArrayList<Task> l = new ArrayList<>();
        l.add(task1);
        TaskList list = new TaskList(l);
        Task t = list.markAsDone("1");
        assertEquals("[T][X]shower", t.toString());
    }

    @Test
    public void markAsDoneTest2() {
        try {
            ToDos task1 = new ToDos("shower");
            ArrayList<Task> l = new ArrayList<>();
            l.add(task1);
            TaskList list = new TaskList(l);
            Task t = list.markAsDone("2");
            fail();
        } catch (DukeException e) {
            assertEquals("Task does not exist. " +
                    "Please send a correct task number ><", e.getMessage());
        }
    }
}
