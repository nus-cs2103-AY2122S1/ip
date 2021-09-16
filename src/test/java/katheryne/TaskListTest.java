package katheryne;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import katheryne.task.Event;
import katheryne.task.Task;
import katheryne.task.Todo;

public class TaskListTest {
    @Test
    public void addAll_twoTasks_sameAsAddIndividually() {
        TaskList taskListAddIndividually = new TaskList();
        TaskList taskListAddAll = new TaskList();
        ArrayList<Task> taskArr = new ArrayList<>();

        Todo todo1 = new Todo("adding a task to an array list");
        Todo todo2 = new Todo("adding a task to an array list, again");
        taskArr.add(todo1);
        taskArr.add(todo2);

        taskListAddAll.addAll(taskArr);
        taskListAddIndividually.add(todo1);
        taskListAddIndividually.add(todo2);

        assertEquals(taskListAddIndividually, taskListAddAll);
    }

    @Test
    public void add_addEvent() {
        Event event = new Event("An event task, second", LocalDate.parse("2021-03-14"));
        TaskList taskList = new TaskList();
        taskList.add(new Event("An event task, second", LocalDate.parse("2021-03-14")));
        assertEquals(taskList.getTask(0), event);
    }

    @Test
    public void deleteTask_incorrectIndex_returnsFalse() {
        try {
            TaskList taskList = new TaskList();
            taskList.add(new Event("An event task, second", LocalDate.parse("2021-03-14")));
            boolean isDeleted = taskList.deleteTask(5);
            assertEquals(isDeleted, false);
        } catch (Exception e) {
            fail();
        }
    }
}
