import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import bobbybot.exceptions.BobbyException;
import bobbybot.exceptions.TaskNoOutOfBoundsException;
import bobbybot.tasks.Deadline;
import bobbybot.tasks.Event;
import bobbybot.tasks.Task;
import bobbybot.tasks.ToDo;
import bobbybot.util.TaskList;

/**
 * Tests for TaskList
 */
public class TaskListTest {
    private final TaskList tasks = new TaskList(new ArrayList<>());
    private final DateTimeFormatter dtFormatter = DateTimeFormatter.ofPattern("dd-MM-uuuu HH:mm");

    @Test
    public void addTask_addToDo_success() {
        ToDo taskToAdd = new ToDo("test description");
        tasks.addTask(taskToAdd);
        assertEquals(tasks.getTasks().size(), 1);
        assertEquals("[T][ ] test description", tasks.getTask(0).toString());
    }

    @Test
    public void addTask_addDeadline_success() {
        LocalDateTime dateBy = LocalDateTime.parse("01-01-2021 12:00", dtFormatter);
        Deadline deadlineToAdd = new Deadline("test description", dateBy);
        tasks.addTask(deadlineToAdd);
        assertEquals(tasks.getTasks().size(), 1);
        assertEquals("[D][ ] test description (by: Jan 01 2021 12:00)", tasks.getTask(0).toString());
    }

    @Test
    public void addTask_addEvent_success() {
        Event eventToAdd = new Event("test description", "this to that");
        tasks.addTask(eventToAdd);
        assertEquals(tasks.getTasks().size(), 1);
        assertEquals("[E][ ] test description (at: this to that)", tasks.getTask(0).toString());
    }

    @Test
    public void deleteTask_deleteTask_success() {
        Task taskToAdd = new Task("test description");
        tasks.addTask(taskToAdd);
        try {
            tasks.deleteTask(1);
        } catch (BobbyException e) {
            System.out.println(e.getMessage());
        }
        assertEquals(0, tasks.getTasks().size());
    }

    @Test
    public void deleteTask_invalidTaskNo_exceptionThrown() {
        Assertions.assertThrows(TaskNoOutOfBoundsException.class, () -> {
            Task taskToAdd = new Task("test description");
            tasks.addTask(taskToAdd);
            tasks.deleteTask(-1);
        });
    }

    @Test
    public void deleteTask_taskNoMoreThanTasksSize_exceptionThrown() {
        Assertions.assertThrows(TaskNoOutOfBoundsException.class, () -> {
            Task taskToAdd = new Task("test description");
            tasks.addTask(taskToAdd);
            tasks.deleteTask(2);
        });
    }
    @Test
    public void markAsDone_markTask_success() {
        Event eventToAdd = new Event("test description", "this to that");
        tasks.addTask(eventToAdd);
        assertEquals("[E][ ] test description (at: this to that)", tasks.getTask(0).toString());
        try {
            tasks.markAsDone(1);
        } catch (TaskNoOutOfBoundsException e) {
            System.out.println(e.getMessage());
        }
        assertEquals("[E][X] test description (at: this to that)", tasks.getTask(0).toString());
    }

    @Test
    public void markAsDone_markInvalidTask_exceptionThrown() {
        Assertions.assertThrows(TaskNoOutOfBoundsException.class, () -> {
            tasks.markAsDone(1);
        });
    }
}
