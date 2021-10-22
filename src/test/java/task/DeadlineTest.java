package task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

import duke.task.Deadline;
import duke.task.Task;

public class DeadlineTest {
    @Test
    public void encode_todo_success() {
        Task task = new Deadline("some deadline", false,
                LocalDate.parse("1929-02-10", DateTimeFormatter.ISO_LOCAL_DATE));
        assertEquals("d\u001E0\u001E1929-02-10\u001Esome deadline", task.encode());
    }

    @Test
    public void toString_todo_success() {
        Task task = new Deadline("some other deadline", true,
                LocalDate.parse("3040-04-29", DateTimeFormatter.ISO_LOCAL_DATE));
        assertEquals("[D][X] some other deadline (by: 29 Apr 3040)", task.toString());
    }
}
