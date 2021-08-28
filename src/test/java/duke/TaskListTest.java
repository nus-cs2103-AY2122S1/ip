package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    @Test
    public void Test1(){
        Task todo = new Todo("read book");
        Task deadline = new Deadline("return book"," 2020-08-12");
        TaskList tasks = new TaskList();
        tasks.add(todo);
        tasks.add(deadline);
        assertEquals(2,tasks.size());
        tasks.remove(1);
        assertEquals(1,tasks.size());
        assertEquals(todo,tasks.get(0));
    }
}
