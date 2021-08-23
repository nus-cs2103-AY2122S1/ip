package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DukeTest {
    @Test
    public void testAddTask() {
        TaskList tasks = new TaskList();
        tasks.action("todo task1");
        tasks.action("event task2 /2021-08-24");
        tasks.action("event task3 /2021-08-25");

        String output = "[T][ ] task1\n" +
                "[E][ ] task2 (at: Aug 24 2021)\n" +
                "[E][ ] task3 (at: Aug 25 2021)\n";
        assertEquals(tasks.output(), output);
    }

    @Test
    public void testDoneTask() {
        TaskList tasks = new TaskList();
        tasks.action("todo task1");
        tasks.action("done 1");

        String output = "[T][X] task1\n";
        assertEquals(tasks.output(), output);
    }

    @Test
    public void testDeleteTask() {
        TaskList tasks = new TaskList();
        tasks.action("todo task1");
        tasks.action("todo task2");
        tasks.action("delete 2");

        String output = "[T][ ] task1\n";
        assertEquals(tasks.output(), output);
    }
}
