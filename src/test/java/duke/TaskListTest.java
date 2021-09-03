package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import duke.task.ToDoTask;

public class TaskListTest {
    @Test
    public void addTaskTest() {
        TaskList temp = new TaskList(new ArrayList<>());
        temp.addTask(new ToDoTask("read a book"));
        assertEquals(new ToDoTask("read a book").toString() , temp.getTask(0).toString());
    }

    @Test
    public void deleteTaskAtIndexTest() {
        TaskList temp = new TaskList(new ArrayList<>());
        temp.addTask(new ToDoTask("read a book"));
        assertEquals(temp.getTask(0).toString(), temp.deleteTaskAtIndex(0).toString());
    }

    @Test
    public void getTaskTest() {
        TaskList temp = new TaskList(new ArrayList<>());
        temp.addTask(new ToDoTask("read a book"));
        assertEquals(new ToDoTask("read a book").toString(), temp.getTask(0).toString());
    }

    @Test
    public void getTaskListLengthTest() {
        TaskList temp = new TaskList(new ArrayList<>());
        temp.addTask(new ToDoTask("read a book"));
        assertEquals(1, temp.getTaskListLength());
    }

    @Test
    public void completeTaskTest() {
        TaskList temp = new TaskList(new ArrayList<>());
        temp.addTask(new ToDoTask("read a book"));
        temp.getTask(0).markAsCompleted();
        assertEquals("[T][X] read a book", temp.getTask(0).toString());
    }
}
