package task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;

public class TaskListTest {
    @Test
    public void addTask_todo_success() {
        TaskList tasks = new TaskList();
        tasks.add(new Todo("Finish iP"));
        assertEquals("T | 0 | Finish iP | ", tasks.toString());
    }

    @Test
    public void get_todo_success() {
        TaskList tasks = new TaskList();
        tasks.add(new Todo("Finish iP1"));
        tasks.add(new Todo("Finish iP2"));
        tasks.add(new Todo("Finish iP3"));
        assertEquals("[T][ ] Finish iP2", tasks.get(1).toString());
    }

    @Test
    public void remove_todo_success() {
        TaskList tasks = new TaskList();
        tasks.add(new Todo("Finish iP1"));
        tasks.add(new Todo("Finish iP2"));
        tasks.add(new Todo("Finish iP3"));
        assertEquals("[T][ ] Finish iP2", tasks.get(1).toString());
        tasks.remove(1);
        assertNotEquals("[T][ ] Finish iP2", tasks.get(1).toString());
    }

    @Test
    public void size() {
        TaskList tasks1 = new TaskList();
        tasks1.add(new Todo("Finish iP1"));
        tasks1.add(new Todo("Finish iP2"));
        tasks1.add(new Todo("Finish iP3"));
        assertEquals(3, tasks1.size());

        ArrayList<Task> tasks2 = new ArrayList<>();
        tasks2.add(new Todo("Finish iP1"));
        tasks2.add(new Todo("Finish iP2"));
        tasks2.add(new Todo("Finish iP3"));
        assertEquals(3, tasks2.size());
    }

    @Test
    public void sort() {
        TaskList tasks = new TaskList();
        tasks.add(new Todo("z"));
        tasks.add(new Todo("a"));
        assertEquals("1.[T][ ] z\n2.[T][ ] a", tasks.print());
        tasks.sort();
        assertEquals("1.[T][ ] a\n2.[T][ ] z", tasks.print());
    }
}
