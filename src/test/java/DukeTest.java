import duke.exception.DukeIndexInputException;
import duke.exception.DukeTaskDetailsException;
import duke.task.Todo;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.TaskList;
import duke.task.Task;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DukeTest {
    @Test
    public void testTodo(){
        Todo todo = new Todo("walk my dog");
        assertEquals(todo.toString(), "[T][ ] walk my dog");
        todo.markAsDone();
        assertEquals(todo.toString(), "[T][X] walk my dog");
    }

    @Test
    public void testDeadline() {
        Deadline deadline = new Deadline("homework", "2021-08-29 2359");
        assertEquals(deadline.toString(), "[D][ ] homework(by: Aug 29 2021  23:59)");
        deadline.markAsDone();
        assertEquals(deadline.toString(), "[D][X] homework(by: Aug 29 2021  23:59)");
    }

    @Test
    public void testEssay() {
        Event event = new Event("movie", "2021-08-29 1830");
        assertEquals(event.toString(), "[E][ ] movie(at: Aug 29 2021  18:30)");
        event.markAsDone();
        assertEquals(event.toString(), "[E][X] movie(at: Aug 29 2021  18:30)");
    }

    @Test
    public void testTaskListIndexCommand() {
        Todo todo = new Todo("walk my dog");
        Deadline deadline = new Deadline("homework", "2021-08-29 2359");
        Event event = new Event("movie", "2021-08-29 1830");
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(todo);
        tasks.add(deadline);
        tasks.add(event);
        TaskList tasklist = new TaskList(tasks);
        assertEquals(tasklist.size(), 3);
        try {
            tasklist.indexCommand(new String[] {"done", "1"});
            tasklist.indexCommand(new String[] {"delete", "2"});
        } catch (DukeIndexInputException e) {} // won't happen as tasklist is initialised with 3 elements
        assertEquals(todo.toString(), "[T][X] walk my dog");
        assertEquals(tasklist.size(), 2);
    }

    @Test
    public void testTaskListAddCommand() {
        TaskList tasklist = new TaskList(new ArrayList<>());
        try {
            tasklist.addTask(new String[] {"todo", "walk my dog"});
            tasklist.addTask(new String[] {"deadline", "homework /by 2021-08-29 23:59"});
            tasklist.addTask(new String[] {"event", "movie /at 2021-08-29 18:30"});
        } catch (DukeTaskDetailsException e) {}
        assertEquals(tasklist.size(), 3);
    }
}
