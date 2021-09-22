package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TaskListTest {

    @Test
    void testStringConversion() {
        duke.TaskList tasklist = new duke.TaskList();
        try {
            duke.Deadline deadline = new duke.Deadline("return book", "02/02/2022 1800");
            duke.Event event = new duke.Event("date with Mary Jane", "05/11/2024 2000");
            duke.ToDo todo1 = new duke.ToDo("go for a run");
            duke.ToDo todo2 = new duke.ToDo("eat dinner");
            tasklist.add(deadline);
            tasklist.add(event);
            tasklist.add(todo1);
            tasklist.add(todo2);
        } catch (DukeException e) {
            //there should not be an exception here.
        }
        try {
            tasklist.done(3);
        } catch (DukeException e) {
            fail();
        }
        String expected = "1. [D][ ] return book (by: 02 Feb 2022 06:00PM)\n" +
                "2. [E][ ] date with Mary Jane (at: 05 Nov 2024 08:00PM)\n" +
                "3. [T][X] go for a run\n" +
                "4. [T][ ] eat dinner\n";
        assertEquals(expected, tasklist.toString());
    }

    @Test
    void testNumberOfTasks() {
        duke.TaskList tasklist1 = new duke.TaskList();
        try {
            duke.Deadline deadline = new duke.Deadline("return book", "02/02/2022 1800");
            duke.Event event = new duke.Event("date with Mary Jane", "05/11/2024 2000");
            duke.ToDo todo1 = new duke.ToDo("go for a run");
            duke.ToDo todo2 = new duke.ToDo("eat dinner");
            tasklist1.add(deadline);
            tasklist1.add(event);
            tasklist1.add(todo1);
            tasklist1.add(todo2);
        } catch (DukeException e) {
            //there should not be an exception here.
        }
        try {
            tasklist1.done(3);
        } catch (DukeException e) {
            fail();
        }
        duke.TaskList tasklist2 = new duke.TaskList();
        assertEquals(4, tasklist1.getNoOfTasks());
        assertEquals(0, tasklist2.getNoOfTasks());
    }

}
