package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import duke.tasks.Todo;

public class TaskListTest {
    @Test
    public void check_desc_test() {
        try {
            TaskList temp = new TaskList();
            temp.add(new Todo("return book", false));
            assertEquals("return book", temp.get(temp.size() - 1).getDescription());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void set_done_test() {
        try {
            TaskList tasks = new TaskList();
            tasks.add(new Todo("return book", false));
            tasks.markAsDone(0);
            assertEquals(tasks.get(0).isDone(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
