package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

public class TaskListTest {
    private ArrayList<Task> al = new ArrayList<>();
    private TaskList taskList;
    private final Todo NEW_TODO = new Todo("todo description");
    private final Deadline NEW_DEADLINE = new Deadline("deadline description", "2021-12-21 12:21");
    private final Event NEW_EVENT = new Event("event description", "2021-12-21 12:21");

    @Test
    public void addTaskTest() {
        taskList = new TaskList(al);
        taskList.addTask(NEW_DEADLINE);
        assertEquals("1.[D][ ] deadline description (by: 21 Dec 2021 - 12:21)",
                taskList.toString());
    }

    @Test
    public void doneTest() {
        al.clear();
        al.add(NEW_TODO);
        al.add(NEW_DEADLINE);
        al.add(NEW_EVENT);
        taskList = new TaskList(al);
        assertEquals("[D][X] deadline description (by: 21 Dec 2021 - 12:21)",
                taskList.markAsDone(1));
        assertEquals("1.[T][ ] todo description\n"
                + "2.[D][X] deadline description (by: 21 Dec 2021 - 12:21)\n"
                + "3.[E][ ] event description (at: 21 Dec 2021 - 12:21)",
                taskList.toString());
    }

    @Test
    public void getSizeTest() {
        al.clear();
        al.add(NEW_TODO);
        al.add(NEW_DEADLINE);
        al.add(NEW_EVENT);
        taskList = new TaskList(al);
        assertEquals(3, taskList.getSize());
    };
}
