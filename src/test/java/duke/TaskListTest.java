package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    @Test
    public void markDoneTaskTest(){
        TaskList tl = new TaskList();
        tl.addTodo("greet the users!");
        tl.markDone(1);
        boolean ans = tl.getList().get(0).isCompleted();
        assertEquals(true, ans);
    }

    @Test
    public void deleteTaskTest(){
        TaskList tl = new TaskList();
        tl.addTodo("todo greet the users!");
        tl.addDeadline("deadline sleep /by 2021-11-25 1800");
        tl.deleteTask(1);
        int ans = tl.size();
        assertEquals(1, ans);
    }
}