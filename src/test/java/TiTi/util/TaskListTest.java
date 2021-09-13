package titi.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import titi.task.ToDo;


public class TaskListTest {

    @Test
    public void addTest() {
        TaskList testTaskList = new TaskList(new ArrayList<>());
        testTaskList.add(new ToDo("revision"));
        assertEquals(testTaskList.get(0).toString(), "[T][ ] revision");
    }

    @Test
    public void sizeTest() {
        TaskList testTaskList = new TaskList(new ArrayList<>());
        testTaskList.add(new ToDo("revision"));
        testTaskList.add(new ToDo("revision"));
        testTaskList.add(new ToDo("revision"));
        testTaskList.add(new ToDo("revision"));
        assertEquals(testTaskList.size(), 4);
    }

    @Test
    public void removeTest() {
        TaskList testTaskList = new TaskList(new ArrayList<>());
        ToDo todo = new ToDo("delete");
        testTaskList.add(todo);
        testTaskList.add(new ToDo("test 2"));
        testTaskList.add(new ToDo("test 3"));
        testTaskList.remove(todo);
        assertEquals(testTaskList.get(0).toString(), "[T][ ] test 2");
    }

}
