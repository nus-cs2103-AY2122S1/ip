package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TaskListTest {
    static List<Task> empty = new ArrayList<>();
    static List<Task> tasks = new ArrayList<>(Arrays.asList(
            new Todo("description"),
            new Deadline("deadline", LocalDate.parse("2021-08-09")),
            new Event("event", LocalDate.parse("2021-08-09"))));

    @Test
    void constructor_emptyList_success() {
        TaskList taskLists = new TaskList(empty);
        assertEquals("Hey! You have not added any task...\n", taskLists.toString());
    }

    @Test
    void constructor_nonEmptyList_success() {
        TaskList taskLists = new TaskList(tasks);
        String expected =  "Here are the tasks in your list:\n" +
                "1. [T][ ] description\n" +
                "2. [D][ ] deadline(by: 08 09 2021)\n" +
                "3. [E][ ] event(at: 08 09 2021)\n";
        assertEquals(expected, taskLists.toString());
    }

    @Test
    void remove_existingTask_success() {
        TaskList taskLists = new TaskList(tasks);
        String existing =  "Here are the tasks in your list:\n" +
                "1. [T][ ] description\n" +
                "2. [D][ ] deadline(by: 08 09 2021)\n" +
                "3. [E][ ] event(at: 08 09 2021)\n";
        assertEquals(existing, taskLists.toString());

        taskLists.remove(2);
        String expected = "Here are the tasks in your list:\n" +
                "1. [T][ ] description\n" +
                "2. [E][ ] event(at: 08 09 2021)\n";
        assertEquals(expected, taskLists.toString());
    }

    @Test
    void remove_nonExistingTask_throw() {
        TaskList taskLists = new TaskList(empty);
        assertEquals("Hey! You have not added any task...\n", taskLists.toString());

        assertThrows(IndexOutOfBoundsException.class, () -> taskLists.remove(2));
    }

}
