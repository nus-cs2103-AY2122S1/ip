import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

import iris.exception.IrisException;
import iris.exception.NoSuchTaskException;
import iris.task.Deadline;
import iris.task.Event;
import iris.task.TaskList;
import iris.task.ToDo;

public class TaskListTest {

    @Test
    public void toDoStringToTaskNotDone() {
        ToDo toDo = new ToDo("run", false);
        assertEquals(toDo.toString(), TaskList.stringToTask("T | 0 | run").toString());
    }

    @Test
    public void toDoStringToTaskDone() {
        ToDo toDo = new ToDo("run", true);
        assertEquals(toDo.toString(), TaskList.stringToTask("T | 1 | run").toString());
    }

    @Test
    public void deadlineStringToTaskNotDone() {
        String commandDetails = "return book /by 2021-08-31 15:00";
        int byIndex = commandDetails.indexOf("/by") - 1;
        String deadlineDetails = commandDetails.substring(0, byIndex);
        LocalDateTime by = LocalDateTime.parse(commandDetails.substring(commandDetails.indexOf("by") + 3),
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        Deadline deadline = new Deadline(deadlineDetails, false, by);
        assertEquals(deadline.toString(), TaskList.stringToTask("D | 0 | return book | 2021-08-31T15:00").toString());
    }

    @Test
    public void deadlineStringToTaskDone() {
        String commandDetails = "return book /by 2021-08-31 15:00";
        int byIndex = commandDetails.indexOf("/by") - 1;
        String deadlineDetails = commandDetails.substring(0, byIndex);
        LocalDateTime by = LocalDateTime.parse(commandDetails.substring(commandDetails.indexOf("by") + 3),
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        Deadline deadline = new Deadline(deadlineDetails, true, by);
        assertEquals(deadline.toString(), TaskList.stringToTask("D | 1 | return book | 2021-08-31T15:00").toString());
    }

    @Test
    public void eventStringToTaskNotDone() {
        String commandDetails = "project meeting /at 2021-08-31 15:00";
        int atIndex = commandDetails.indexOf("/at") - 1;
        String eventDetails = commandDetails.substring(0, atIndex);
        LocalDateTime at = LocalDateTime.parse(commandDetails.substring(commandDetails.indexOf("at") + 3),
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        Event event = new Event(eventDetails, false, at);
        assertEquals(event.toString(), TaskList.stringToTask("E | 0 | project meeting | 2021-08-31T15:00").toString());
    }

    @Test
    public void eventStringToTaskDone() {
        String commandDetails = "project meeting /at 2021-08-31 15:00";
        int atIndex = commandDetails.indexOf("/at") - 1;
        String eventDetails = commandDetails.substring(0, atIndex);
        LocalDateTime at = LocalDateTime.parse(commandDetails.substring(commandDetails.indexOf("at") + 3),
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        Event event = new Event(eventDetails, true, at);
        assertEquals(event.toString(), TaskList.stringToTask("E | 1 | project meeting | 2021-08-31T15:00").toString());
    }

    @Test
    public void taskListAddTask() throws NoSuchTaskException {
        ToDo toDo = new ToDo("run", false);

        String commandDetails = "return book /by 2021-08-31 15:00";
        int byIndex = commandDetails.indexOf("/by") - 1;
        String deadlineDetails = commandDetails.substring(0, byIndex);
        LocalDateTime by = LocalDateTime.parse(commandDetails.substring(commandDetails.indexOf("by") + 3),
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        Deadline deadline = new Deadline(deadlineDetails, false, by);

        String commandDetails2 = "project meeting /at 2021-08-31 15:00";
        int atIndex = commandDetails2.indexOf("/at") - 1;
        String eventDetails = commandDetails2.substring(0, atIndex);
        LocalDateTime at = LocalDateTime.parse(commandDetails2.substring(commandDetails2.indexOf("at") + 3),
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        Event event = new Event(eventDetails, true, at);

        TaskList taskList = new TaskList();
        taskList.addTask(toDo);
        taskList.addTask(deadline);
        taskList.addTask(event);

        assertEquals(toDo.toString(), taskList.getTask(1).toString());
        assertEquals(deadline.toString(), taskList.getTask(2).toString());
        assertEquals(event.toString(), taskList.getTask(3).toString());
        assertEquals(3, taskList.size());
    }

    @Test
    public void taskListGetTask() throws NoSuchTaskException {
        ToDo toDo = new ToDo("run", false);

        String commandDetails = "return book /by 2021-08-31 15:00";
        int byIndex = commandDetails.indexOf("/by") - 1;
        String deadlineDetails = commandDetails.substring(0, byIndex);
        LocalDateTime by = LocalDateTime.parse(commandDetails.substring(commandDetails.indexOf("by") + 3),
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        Deadline deadline = new Deadline(deadlineDetails, false, by);

        String commandDetails2 = "project meeting /at 2021-08-31 15:00";
        int atIndex = commandDetails2.indexOf("/at") - 1;
        String eventDetails = commandDetails2.substring(0, atIndex);
        LocalDateTime at = LocalDateTime.parse(commandDetails2.substring(commandDetails2.indexOf("at") + 3),
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        Event event = new Event(eventDetails, true, at);

        TaskList taskList = new TaskList();
        taskList.addTask(toDo);
        taskList.addTask(deadline);
        taskList.addTask(event);

        assertEquals(toDo.toString(), taskList.getTask(1).toString());
        assertEquals(deadline.toString(), taskList.getTask(2).toString());
        assertEquals(event.toString(), taskList.getTask(3).toString());
    }

    @Test
    public void taskListDeleteTask() throws NoSuchTaskException {
        ToDo toDo = new ToDo("run", false);

        String commandDetails = "return book /by 2021-08-31 15:00";
        int byIndex = commandDetails.indexOf("/by") - 1;
        String deadlineDetails = commandDetails.substring(0, byIndex);
        LocalDateTime by = LocalDateTime.parse(commandDetails.substring(commandDetails.indexOf("by") + 3),
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        Deadline deadline = new Deadline(deadlineDetails, false, by);

        String commandDetails2 = "project meeting /at 2021-08-31 15:00";
        int atIndex = commandDetails2.indexOf("/at") - 1;
        String eventDetails = commandDetails2.substring(0, atIndex);
        LocalDateTime at = LocalDateTime.parse(commandDetails2.substring(commandDetails2.indexOf("at") + 3),
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        Event event = new Event(eventDetails, true, at);

        TaskList taskList = new TaskList();
        taskList.addTask(toDo);
        taskList.addTask(deadline);
        taskList.addTask(event);

        assertEquals(toDo.toString(), taskList.getTask(1).toString());
        assertEquals(deadline.toString(), taskList.getTask(2).toString());
        assertEquals(event.toString(), taskList.getTask(3).toString());

        try {
            taskList.deleteTask(2);
        } catch (IrisException e) {
            System.out.println(e.getMessage());
        }

        assertEquals(toDo.toString(), taskList.getTask(1).toString());
        assertEquals(event.toString(), taskList.getTask(2).toString());

    }

    @Test
    public void taskListMarkAsDone() {
        ToDo toDo = new ToDo("read book", false);

        String commandDetails = "return book /by 2021-08-31 15:00";
        int byIndex = commandDetails.indexOf("/by") - 1;
        String deadlineDetails = commandDetails.substring(0, byIndex);
        LocalDateTime by = LocalDateTime.parse(commandDetails.substring(commandDetails.indexOf("by") + 3),
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        Deadline deadline = new Deadline(deadlineDetails, false, by);

        String commandDetails2 = "project meeting /at 2021-08-31 15:00";
        int atIndex = commandDetails2.indexOf("/at") - 1;
        String eventDetails = commandDetails2.substring(0, atIndex);
        LocalDateTime at = LocalDateTime.parse(commandDetails2.substring(commandDetails2.indexOf("at") + 3),
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        Event event = new Event(eventDetails, false, at);

        TaskList taskList = new TaskList();

        taskList.addTask(toDo);
        taskList.addTask(deadline);
        taskList.addTask(event);

        assertEquals("[T][ ] read book", toDo.getStatus());
        assertEquals("[D][ ] return book (by: 31-08-2021 15:00)", deadline.getStatus());
        assertEquals("[E][ ] project meeting (at: 31-08-2021 15:00)", event.getStatus());

        try {
            taskList.markAsDone(1);
            taskList.markAsDone(2);
            taskList.markAsDone(3);
        } catch (IrisException e) {
            System.out.println(e.getMessage());
        }

        assertEquals("[T][X] read book", toDo.getStatus());
        assertEquals("[D][X] return book (by: 31-08-2021 15:00)", deadline.getStatus());
        assertEquals("[E][X] project meeting (at: 31-08-2021 15:00)", event.getStatus());
    }

    @Test
    public void taskListSize() {
        ToDo toDo = new ToDo("run", false);

        String commandDetails = "return book /by 2021-08-31 15:00";
        int byIndex = commandDetails.indexOf("/by") - 1;
        String deadlineDetails = commandDetails.substring(0, byIndex);
        LocalDateTime by = LocalDateTime.parse(commandDetails.substring(commandDetails.indexOf("by") + 3),
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        Deadline deadline = new Deadline(deadlineDetails, false, by);

        String commandDetails2 = "project meeting /at 2021-08-31 15:00";
        int atIndex = commandDetails2.indexOf("/at") - 1;
        String eventDetails = commandDetails2.substring(0, atIndex);
        LocalDateTime at = LocalDateTime.parse(commandDetails2.substring(commandDetails2.indexOf("at") + 3),
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        Event event = new Event(eventDetails, true, at);

        TaskList taskList = new TaskList();
        taskList.addTask(toDo);
        taskList.addTask(deadline);
        taskList.addTask(event);

        assertEquals(3, taskList.size());

        taskList.addTask(toDo);
        taskList.addTask(deadline);
        taskList.addTask(event);

        assertEquals(6, taskList.size());

        try {
            taskList.deleteTask(3);
        } catch (IrisException e) {
            System.out.println(e.getMessage());
        }

        assertEquals(5, taskList.size());
    }

    @Test
    public void taskListGetList() {
        ToDo toDo = new ToDo("run", false);

        String commandDetails = "return book /by 2021-08-31 15:00";
        int byIndex = commandDetails.indexOf("/by") - 1;
        String deadlineDetails = commandDetails.substring(0, byIndex);
        LocalDateTime by = LocalDateTime.parse(commandDetails.substring(commandDetails.indexOf("by") + 3),
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        Deadline deadline = new Deadline(deadlineDetails, false, by);

        String commandDetails2 = "project meeting /at 2021-08-31 15:00";
        int atIndex = commandDetails2.indexOf("/at") - 1;
        String eventDetails = commandDetails2.substring(0, atIndex);
        LocalDateTime at = LocalDateTime.parse(commandDetails2.substring(commandDetails2.indexOf("at") + 3),
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        Event event = new Event(eventDetails, true, at);

        TaskList taskList = new TaskList();
        taskList.addTask(toDo);
        taskList.addTask(deadline);
        taskList.addTask(event);

        assertEquals(taskList.getList().get(0), toDo);
        assertEquals(taskList.getList().get(1), deadline);
        assertEquals(taskList.getList().get(2), event);
    }

    @Test
    public void taskListLoadFromStorage() {
        ToDo toDo = new ToDo("run", false);

        String commandDetails = "return book /by 2021-08-31 15:00";
        int byIndex = commandDetails.indexOf("/by") - 1;
        String deadlineDetails = commandDetails.substring(0, byIndex);
        LocalDateTime by = LocalDateTime.parse(commandDetails.substring(commandDetails.indexOf("by") + 3),
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        Deadline deadline = new Deadline(deadlineDetails, false, by);

        String commandDetails2 = "project meeting /at 2021-08-31 15:00";
        int atIndex = commandDetails2.indexOf("/at") - 1;
        String eventDetails = commandDetails2.substring(0, atIndex);
        LocalDateTime at = LocalDateTime.parse(commandDetails2.substring(commandDetails2.indexOf("at") + 3),
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        Event event = new Event(eventDetails, true, at);

        TaskList storageTaskList = new TaskList();
        storageTaskList.addTask(toDo);
        storageTaskList.addTask(deadline);
        storageTaskList.addTask(event);

        TaskList loadedTaskList = new TaskList();
        loadedTaskList.loadFromStorage(storageTaskList.getList());

        assertEquals(loadedTaskList.getList().get(0), toDo);
        assertEquals(loadedTaskList.getList().get(1), deadline);
        assertEquals(loadedTaskList.getList().get(2), event);
    }
}
