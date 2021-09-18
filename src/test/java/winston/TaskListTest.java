package winston;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {

    @Test
    public void dummyTest() {
        assertEquals(2,2);
    }    
    
    @Test
    public void completedTaskTest() {
        ArrayList<Task> list =  new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            if (i % 2 == 0) {
                list.add(new ToDoTask("test", true));
            } else {
                list.add(new ToDoTask("test", false));
            }
        }
        TaskList taskList = new TaskList(list);
        assertEquals(taskList.numberOfIncompleteTasks(), 10);
    }
    
    @Test
    public void getListDataTest() {
        ArrayList<Task> list =  new ArrayList<>();
        list.add(new ToDoTask("test1", true));
        list.add(new DeadLine("test1 2","2020-12-12", false));
        list.add(new Event("test1 3","2021-01-01", true));
        TaskList taskList = new TaskList(list);
        String str = "T,1,test1\nD,0,test1 2,2020-12-12\nE,1,test1 3,2021-01-01";
        assertEquals(taskList.listSaveDataFormatter(), str);
    }
}
