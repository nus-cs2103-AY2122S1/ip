package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

class TaskListTest {

    @Test
    void get() {
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new ToDo("buy bread", true));
        tasks.add(new Deadline("eat bread", LocalDateTime.parse("2021-08-28T18:00"), false));
        tasks.add(new Event("bread-eating contest", LocalDateTime.parse("2021-09-01T15:00"),
                LocalTime.parse("16:30"), false));
        TaskList taskList = new TaskList(tasks);

        assertEquals(tasks, taskList.get());
    }

    @Test
    void addTask() {
        ArrayList<Task> tasks = new ArrayList<>();
        TaskList taskList = new TaskList(tasks);
        ToDo td = new ToDo("learn to bake bread", false);
        tasks.add(td);
        taskList.addTask(td);

        assertEquals(tasks, taskList.get());
    }

    @Test
    void markTaskAsDone() {
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new ToDo("learn to bake bread", false));
        TaskList taskList = new TaskList(tasks);

        try {
            char checkmark = taskList.get().get(0).toString().charAt(4);
            assertEquals(' ', checkmark);

            taskList.markTaskAsDone(1);
            checkmark = taskList.get().get(0).toString().charAt(4);
            assertEquals('X', checkmark);

            taskList.markTaskAsDone(3);

        } catch (DukeException de) {
            assertEquals("☹ OOPS!!! That task does not exist.", de.getMessage());
        }
    }

    @Test
    void deleteTask() {
        ArrayList<Task> tasks = new ArrayList<>();
        ToDo td = new ToDo("buy bread", true);
        tasks.add(td);
        tasks.add(new Deadline("eat bread", LocalDateTime.parse("2021-08-28T18:00"), false));
        TaskList taskList = new TaskList(tasks);

        try {
            assertEquals(2, taskList.get().size());

            taskList.deleteTask(2);
            assertEquals(1, taskList.get().size());
            assertEquals(td, taskList.get().get(0));

            taskList.deleteTask(2);

        } catch (DukeException de) {
            assertEquals("☹ OOPS!!! That task does not exist.", de.getMessage());
        }
    }
}
