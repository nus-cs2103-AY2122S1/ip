package tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

import duke.exception.DukeException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.tasks.Todo;

public class TaskListTest {
    @Test
    public void addTaskTest() {
        TaskList taskList = new TaskList();
        Task task = new Task("sleep");
        taskList.addToList(task);
        String expected = "[ ] sleep";
        String actual = taskList.getTaskList().get(0).toString();
        assertEquals(expected, actual);
    }

    @Test
    public void deleteTaskTest() {
        TaskList taskList = new TaskList();
        Task task1 = new Task("sleep");
        Task task2 = new Task("sleep again");

        taskList.addToList(task1);
        taskList.addToList(task2);

        try {
            taskList.deleteTask(0);
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }

        String expected = "[ ] sleep again";
        String actual = taskList.getTaskList().get(0).toString();
        assertEquals(expected, actual);
    }

    @Test
    public void addTodoTest() {
        TaskList taskList = new TaskList();
        Todo todo = new Todo("sleep");
        taskList.addToList(todo);
        String expected = "[T][ ] sleep";
        String actual = taskList.getTaskList().get(0).toString();
        assertEquals(expected, actual);
    }

    @Test
    public void deleteTodoTest() {
        TaskList taskList = new TaskList();
        Todo todo1 = new Todo("sleep");
        Todo todo2 = new Todo("sleep again");

        taskList.addToList(todo1);
        taskList.addToList(todo2);

        try {
            taskList.deleteTask(0);
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }

        String expected = "[T][ ] sleep again";
        String actual = taskList.getTaskList().get(0).toString();
        assertEquals(expected, actual);
    }

    @Test
    public void addEventTest() {
        TaskList taskList = new TaskList();

        LocalDate date = LocalDate.parse("2020/02/02", DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        LocalTime timeStart = LocalTime.parse("2230", DateTimeFormatter.ofPattern("HHmm"));
        LocalTime timeEnd = LocalTime.parse("0600", DateTimeFormatter.ofPattern("HHmm"));

        Event event = new Event("sleep", date, timeStart, timeEnd);

        taskList.addToList(event);

        String expected = "[E][ ] sleep (at: Feb 2 2020, 10:30 PM - 6:00 AM)";
        String actual = taskList.getTaskList().get(0).toString();
        assertEquals(expected, actual);
    }

    @Test
    public void deleteEventTest() {
        TaskList taskList = new TaskList();

        LocalDate date = LocalDate.parse("2020/02/02", DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        LocalTime timeStart = LocalTime.parse("2230", DateTimeFormatter.ofPattern("HHmm"));
        LocalTime timeEnd = LocalTime.parse("0600", DateTimeFormatter.ofPattern("HHmm"));

        Event event1 = new Event("sleep", date, timeStart, timeEnd);
        Event event2 = new Event("sleep again", date, timeStart, timeEnd);

        taskList.addToList(event1);
        taskList.addToList(event2);

        try {
            taskList.deleteTask(0);
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }

        String expected = "[E][ ] sleep again (at: Feb 2 2020, 10:30 PM - 6:00 AM)";
        String actual = taskList.getTaskList().get(0).toString();
        assertEquals(expected, actual);
    }

    @Test
    public void addDeadlineTest() {
        TaskList taskList = new TaskList();

        LocalDateTime dateTime = LocalDateTime.parse("2020/02/02 2230", DateTimeFormatter.ofPattern("yyyy/MM/dd HHmm"));

        Deadline deadline = new Deadline("sleep", dateTime);

        taskList.addToList(deadline);

        String expected = "[D][ ] sleep (by: Feb 2 2020, 10:30 PM)";
        String actual = taskList.getTaskList().get(0).toString();
        assertEquals(expected, actual);
    }

    @Test
    public void deleteDeadlineTest() {
        TaskList taskList = new TaskList();

        LocalDateTime dateTime = LocalDateTime.parse("2020/02/02 2230", DateTimeFormatter.ofPattern("yyyy/MM/dd HHmm"));

        Deadline deadline1 = new Deadline("sleep", dateTime);
        Deadline deadline2 = new Deadline("sleep again", dateTime);

        taskList.addToList(deadline1);
        taskList.addToList(deadline2);

        try {
            taskList.deleteTask(0);
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }

        String expected = "[D][ ] sleep again (by: Feb 2 2020, 10:30 PM)";
        String actual = taskList.getTaskList().get(0).toString();
        assertEquals(expected, actual);
    }
}
