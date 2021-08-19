package task;

import exception.DukeIOException;
import exception.DukeTaskNumberOutOfBoundsException;
import org.junit.jupiter.api.Test;
import util.DateTimeUtils;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class TaskManagerTest {

    @Test
    void addTask() {
        TaskManager taskManager = new TaskManager();
        Todo todo = new Todo("Read book");
        LocalDateTime byDateTime = DateTimeUtils.parseDateTime("2021-08-01 13:00");
        Deadline deadline = new Deadline("Return book", byDateTime);
        taskManager.addTask(todo);
        taskManager.addTask(deadline);
        assertEquals(2, taskManager.size());
    }

    @Test
    void size() {
        TaskManager taskManager = new TaskManager();
        Todo todo = new Todo("Read book");
        LocalDateTime byDateTime = DateTimeUtils.parseDateTime("2021-08-01 13:00");
        Deadline deadline = new Deadline("Return book", byDateTime);
        taskManager.addTask(todo);
        taskManager.addTask(deadline);
        assertEquals(2, taskManager.size());
    }

    @Test
    void findTaskByNumber() {
        TaskManager taskManager = new TaskManager();
        Todo todo = new Todo("Read book");
        LocalDateTime byDateTime = DateTimeUtils.parseDateTime("2021-08-01 13:00");
        Deadline deadline = new Deadline("Return book", byDateTime);
        taskManager.addTask(todo);
        taskManager.addTask(deadline);
        assertEquals(todo, taskManager.findTaskByNumber(1));
    }

    @Test
    void completeTask() throws DukeTaskNumberOutOfBoundsException {
        TaskManager taskManager = new TaskManager();
        Todo todo = new Todo("Read book");
        LocalDateTime byDateTime = DateTimeUtils.parseDateTime("2021-08-01 13:00");
        Deadline deadline = new Deadline("Return book", byDateTime);
        taskManager.addTask(todo);
        taskManager.addTask(deadline);
        taskManager.completeTask(1);
        Todo expected = new Todo("Read book", true);
        assertEquals(expected.toString(), taskManager.findTaskByNumber(1).toString());
    }

    @Test
    void deleteTask() throws DukeTaskNumberOutOfBoundsException {
        TaskManager taskManager = new TaskManager();
        Todo todo = new Todo("Read book");
        LocalDateTime byDateTime = DateTimeUtils.parseDateTime("2021-08-01 13:00");
        Deadline deadline = new Deadline("Return book", byDateTime);
        taskManager.addTask(todo);
        taskManager.addTask(deadline);
        taskManager.deleteTask(1);
        assertEquals(1, taskManager.size());
    }

    @Test
    void clearTasks() {
        TaskManager taskManager = new TaskManager();
        Todo todo = new Todo("Read book");
        LocalDateTime byDateTime = DateTimeUtils.parseDateTime("2021-08-01 13:00");
        Deadline deadline = new Deadline("Return book", byDateTime);
        taskManager.addTask(todo);
        taskManager.addTask(deadline);
        taskManager.clearTasks();
        assertEquals(0, taskManager.size());
    }

    @Test
    void printTasks() {
        TaskManager taskManager = new TaskManager();
        Todo todo = new Todo("Read book");
        LocalDateTime byDateTime = DateTimeUtils.parseDateTime("2021-08-01 13:00");
        Deadline deadline = new Deadline("Return book", byDateTime);
        taskManager.addTask(todo);
        taskManager.addTask(deadline);
        String[] excepted = new String[]{
            "1. [T][ ] Read book",
            "2. [D][ ] Return book (by: Aug 01 2021 01:00 pm)"
        };
        assertArrayEquals(excepted, taskManager.printTasks());
    }

    @Test
    void loadTasksFromFile() throws DukeIOException {
        TaskManager taskManager = new TaskManager();
        taskManager.loadTasksFromFile();
        String[] excepted = new String[]{
            "1. [T][X] read book",
            "2. [D][X] return book (by: Jun 06 2021 06:00 pm)",
            "3. [T][X] join sports club",
            "4. [T][ ] borrow book",
            "5. [D][ ] return book (by: Aug 08 2021 10:00 am)",
            "6. [E][ ] project meeting (at: Aug 20 2021 02:00 pm-04:00 pm)"
        };
        assertArrayEquals(excepted, taskManager.printTasks());
    }
}
