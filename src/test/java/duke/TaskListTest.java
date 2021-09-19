package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import duke.task.DeadlineTask;
import duke.task.EventTask;
import duke.task.ToDoTask;

public class TaskListTest {
    @Test
    public void addTaskTest() {
        TaskList temp = new TaskList(new ArrayList<>());
        temp.addTask(new ToDoTask("read a book"));
        temp.addTask(new DeadlineTask("Return The Da Vinci Code", "25/08/2000 2359"));
        temp.addTask(new EventTask("Abhishek's Wedding", "01/09/2000 1800"));
        assertEquals(new ToDoTask("read a book").toString() , temp.getTask(0).toString());
        assertEquals(new DeadlineTask("Return The Da Vinci Code", "25/08/2000 2359").toString(),
                temp.getTask(1).toString());
        assertEquals(new EventTask("Abhishek's Wedding", "01/09/2000 1800").toString(),
                temp.getTask(2).toString());
    }

    @Test
    public void deleteTaskAtIndexTest() {
        TaskList temp = new TaskList(new ArrayList<>());
        temp.addTask(new ToDoTask("read a book"));
        temp.addTask(new DeadlineTask("Return The Da Vinci Code", "25/08/2000 2359"));
        temp.addTask(new EventTask("Abhishek's Wedding", "01/09/2000 1800"));
        assertEquals(new EventTask("Abhishek's Wedding", "01/09/2000 1800").toString(),
                temp.deleteTask(2).toString());
        assertEquals(new DeadlineTask("Return The Da Vinci Code", "25/08/2000 2359").toString(),
                temp.deleteTask(1).toString());
        assertEquals(new ToDoTask("read a book").toString() , temp.deleteTask(0).toString());
    }

    @Test
    public void getTaskTest() {
        TaskList temp = new TaskList(new ArrayList<>());
        temp.addTask(new ToDoTask("Go to the mall"));
        temp.addTask(new ToDoTask("read a book"));
        assertEquals(new ToDoTask("read a book").toString(), temp.getTask(1).toString());
    }

    @Test
    public void getTaskListLengthTest() {
        TaskList temp = new TaskList(new ArrayList<>());
        assertEquals(0, temp.getTaskListLength());
        temp.addTask(new ToDoTask("read a book"));
        assertEquals(1, temp.getTaskListLength());
    }

    @Test
    public void completeTaskTest() {
        TaskList temp = new TaskList(new ArrayList<>());
        temp.addTask(new ToDoTask("read a book"));
        temp.completeTask(0);
        assertEquals("[T][X] read a book", temp.getTask(0).toString());
        temp.addTask(new DeadlineTask("Return The Da Vinci Code", "25/08/2000 2359"));
        temp.completeTask(1);
        assertEquals("[D][X] Return The Da Vinci Code (by: Aug 25 2000, 23:59)",
                temp.getTask(1).toString());

        temp.addTask(new EventTask("Abhishek's Wedding", "01/09/2000 1800"));
        temp.completeTask(2);
        assertEquals("[E][X] Abhishek's Wedding (at: Sep 01 2000, 18:00)",
                temp.getTask(2).toString());
    }
}
