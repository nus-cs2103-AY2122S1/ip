package TiTi.util;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import TiTi.task.ToDo;


public class TaskListTest {

    @Test
    public void addTest(){
        TaskList testTaskList = new TaskList(new ArrayList<>());
        testTaskList.add(new ToDo("revision"));
        assertEquals(testTaskList.get(0).toString(), "[T][ ] revision");
    }

    @Test
    public void sizeTest(){
        TaskList testTaskList = new TaskList(new ArrayList<>());
        testTaskList.add(new ToDo("revision"));
        testTaskList.add(new ToDo("revision"));
        testTaskList.add(new ToDo("revision"));
        testTaskList.add(new ToDo("revision"));
        assertEquals(testTaskList.size(), 4);
    }

    @Test
    public void removeTest(){
        TaskList testTaskList = new TaskList(new ArrayList<>());
        testTaskList.add(new ToDo("test 1"));
        testTaskList.add(new ToDo("test 2"));
        testTaskList.add(new ToDo("test 3"));
        testTaskList.remove(0);
        assertEquals(testTaskList.get(0).toString(), "[T][ ] test 2");
    }

}
