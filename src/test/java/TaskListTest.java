import duke.TaskList;
import java.util.ArrayList;
import duke.exceptions.DeleteOutOfBoundsException;
import duke.exceptions.DoneOutOfBoundsException;
import duke.exceptions.WrongDateFormatException;
import duke.exceptions.WrongTimeFormatException;
import duke.tasks.ToDo;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import duke.tasks.Task;
import duke.tasks.Event;
import duke.tasks.Deadline;

public class TaskListTest {

    @Test
    public void taskList_toString()
            throws WrongDateFormatException, WrongTimeFormatException {
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new ToDo("read book"));
        tasks.add(new Event("return book /at 2/12/2019 1725"));
        TaskList tasklist = new TaskList(tasks);
        String expectedString = "1. [T][] read book" + "\n" + "2. return book (at: DECEMBER 2 2019 5.25pm";
        assertEquals(expectedString, tasklist.toString());
    }

    @Test
    public void taskList_deleteTask_success()
            throws WrongDateFormatException,
            WrongTimeFormatException,
            DeleteOutOfBoundsException {
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new ToDo("read book"));
        tasks.add(new Deadline("return book /by 2/12/2019 1800"));
        TaskList tasklist = new TaskList(tasks);
        Task taskRemoved = tasklist.deleteTask(1);
        assertEquals("[T][] read book", taskRemoved.toString());
    }

    @Test
    public void testTaskList()
            throws WrongDateFormatException, WrongTimeFormatException {
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new ToDo("read book"));
        tasks.add(new Event("return book /at 2/12/2019 1800"));
        TaskList tasklist = new TaskList(tasks);
        assertEquals(tasks, tasklist.getTaskList());
    }

    @Test
    public void taskList_markAsDone_Success()
            throws WrongDateFormatException,
            WrongTimeFormatException,
            DoneOutOfBoundsException {
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new ToDo("read book"));
        tasks.add(new Event("return book /at 2/12/2019 1725"));
        TaskList tasklist = new TaskList(tasks);
        Task doneTask = tasklist.markTaskAsDone(2);
        assertEquals("[E][X] return book (at: DECEMBER 2 2019 5.25pm)", doneTask,toString());
    }

    @Test
    public void taskList_lengthSuccess()
            throws WrongDateFormatException, WrongTimeFormatException {
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new ToDo("read book"));
        tasks.add(new Event("return book /at 2/12/2019 1800"));
        TaskList tasklist = new TaskList(tasks);
        assertEquals(2, tasklist.getTaskListLength());
    }



}