package duke;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

/** Class that handles testing of some of the methods in TaskList class */
public class TaskListTest {

    /** Testing whether the output of the task is as expected */
    @Test
    public void confirmTaskAdded() {
        TaskList tasks = new TaskList("./test/junit.txt");
        LocalDateTime date = LocalDateTime.parse("2015-10-20 1800");
        String taskDescription = "read a book";
        tasks.addDeadline(taskDescription, date);
        Task addedTask = tasks.get(0);
        assertEquals("[D][ ] read a book by: 2015-10-20 18:00", addedTask.toString());
    }

    /** Testing the number of tasks in the list */
    @Test
    public void checkNumberOfTasks() {
        TaskList listOfTasks = new TaskList("./test/junit.txt");
        LocalDateTime date = LocalDateTime.parse("2015-10-20");
        for (int i = 0; i < 10; i++) {
            listOfTasks.addDeadline("read a book", date);
        }
        assertEquals(10 , listOfTasks.size());
    }
}