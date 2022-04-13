package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import duke.tasks.Todo;

public class TaskListTest {
    @Test
    public void check_desc_test() {
        try {
            TaskList tasks = new TaskList();
            tasks.add(new Todo("buy groceries", false));
            assertEquals("buy groceries", tasks.get(tasks.size()).getDescription());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void set_done_test() {
        try {
            TaskList tasks = new TaskList();
            tasks.add(new Todo("return book", false));
            tasks.markAsDone(tasks.size());
            assertEquals(tasks.get(tasks.size()).isDone(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
