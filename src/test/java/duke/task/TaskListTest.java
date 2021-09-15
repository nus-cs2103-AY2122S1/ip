package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import duke.exception.DukeException;
import duke.io.TaskStorage;

class TaskListTest {
    private static TaskList taskList;
    private static ArrayList<Task> savedTasks;

    @BeforeAll
    static void beforeAll() {
        taskList = new TaskList();
        savedTasks = new ArrayList<>(taskList.getList());
    }

    @BeforeEach
    void beforeEach() {
        taskList.getList().clear();
    }

    @AfterEach
    void afterEach() throws DukeException {
        TaskStorage.save(savedTasks);
    }


    @Test
    void addTask() throws DukeException {
        Task todo = new ToDo("name");
        Task deadline = new Deadline("name", LocalDate.now());
        Task event = new Event("name", LocalDate.now());

        taskList.addTask(todo);
        taskList.addTask(deadline);
        taskList.addTask(event);

        ArrayList<Task> expected = new ArrayList<>();
        expected.add(todo);
        expected.add(deadline);
        expected.add(event);

        assertEquals(expected, taskList.getList());
    }

    @Test
    void doTask() throws DukeException {
        Task toDo = new ToDo("name");
        taskList.getList().add(toDo);

        assertFalse(toDo.isDone);
        taskList.doTask(1);
        assertTrue(toDo.isDone);
    }

    @Test
    void deleteTask() throws DukeException {
        Task toDo = new ToDo("name");
        taskList.getList().add(toDo);
        taskList.deleteTask(1);
        assertEquals(new ArrayList<Task>(), taskList.getList());
    }

    @Test
    void deleteDone() throws DukeException {
        Task toDo1 = new ToDo("name");
        Task toDo2 = new ToDo("name");
        taskList.getList().add(toDo1);
        taskList.getList().add(toDo2);

        toDo2.isDone = true;
        taskList.deleteDone();
        ArrayList<Task> expected = new ArrayList<>();
        expected.add(toDo1);
        assertEquals(expected, taskList.getList());
    }

    @Test
    void deleteExpired() throws DukeException {
        Task deadline1 = new Deadline("name", LocalDate.now());
        Task deadline2 = new Deadline("name", LocalDate.parse("2010-01-01"));
        taskList.getList().add(deadline1);
        taskList.getList().add(deadline2);

        taskList.deleteExpired();
        ArrayList<Task> expected = new ArrayList<>();
        expected.add(deadline1);
        assertEquals(expected, taskList.getList());
    }

    @Test
    void list() {
        assertEquals(taskList.list(), "No tasks yet!");

        Task toDo1 = new ToDo("name");
        taskList.getList().add(toDo1);

        String expected = "1. " + toDo1.toString();
        assertEquals(expected, taskList.list());
    }

    @Test
    void find_hasMatchingTasks() {
        Task toDo1 = new ToDo("name");
        Task toDo2 = new ToDo("nam");
        Task toDo3 = new ToDo("ame");
        taskList.getList().add(toDo1);
        taskList.getList().add(toDo2);
        taskList.getList().add(toDo3);


        String expected = "Here are the matching tasks in your list:\n  1. " + toDo1.toString()
                + "\n  2. " + toDo2.toString();
        assertEquals(expected, taskList.find("na"));

        String expected2 = "No matching tasks!";
        assertEquals(expected2, taskList.find("names"));
    }
}
