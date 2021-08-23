package duke.task;

import duke.util.DukeDate;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {
    @Test
    public void testToString(){
        ToDo tempTask = new ToDo("testing the tester");
        assertEquals("[T][ ] testing the tester", tempTask.toString());
    }

    @Test
    public void testCompleteTask() {
        ToDo tempTask = new ToDo("testing the tester");
        tempTask.completeTask();
        assertEquals(tempTask.isCompleted(), true);
    }

    @Test
    public void testGetDescription() {
        ToDo tempTask = new ToDo("testing the tester");
        assertEquals(tempTask.getDescription(), "testing the tester");
    }

    @Test
    public void testCheckTerm() {
        ToDo tempTask = new ToDo("testing the tester");
        assertEquals(tempTask.checkTerm("tester"), true);
    }
}