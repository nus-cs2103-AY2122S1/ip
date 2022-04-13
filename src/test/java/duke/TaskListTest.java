package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import duke.task.Task;
import duke.task.Todo;

class TaskListTest {
    @Test
    void dateFormatting() {
        TaskList taskList = new TaskList();
        assertEquals(LocalDateTime.of(1999, 07, 06, 02, 00), taskList.dateFormatting("06/07/1999 02:00"));
    }

    @Test
    void addToList() {
        try {
            ArrayList<Task> xs = new ArrayList<>();
            ArrayList<Task> lst = new ArrayList<>();
            lst.add(new Todo("Test JUnit"));
            TaskList taskList = new TaskList(xs);
            assertEquals(lst.size(), taskList.addToList("T", "Test JUnit", "NA"));
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }
}
