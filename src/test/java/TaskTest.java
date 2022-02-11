import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import misaki.tasks.Task;

public class TaskTest {
    @Test
    public void addToDo() {
        String description = "todo read book";
        Task task = new Task(description, false, "T");
        assertEquals("[ ] todo read book", task.getTask());
    }

    @Test
    public void markDoneTest() {
        String description = "event say bye /at 2021-03-19T23:58";
        Task task = new Task(description, false, "E");
        assertEquals("Nice! I've marked this task as done:\n"
                + "[X] event say bye /at 2021-03-19T23:58", task.markDone());
    }

    @Test
    public void deleteTest() {
        String description = "deadline say hello /by 2020-09-09T23:59";
        Task task = new Task(description, true, "D");
        assertEquals("[X] deadline say hello /by 2020-09-09T23:59", task.delete());
    }
}
