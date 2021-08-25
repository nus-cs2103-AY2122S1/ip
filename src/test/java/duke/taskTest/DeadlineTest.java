package duke.taskTest;

import duke.parser.DateTimeParser;
import duke.task.Deadline;
import duke.task.Task;
import duke.task.ToDo;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DeadlineTest {

    @Test
    public void toStringTest() {
        LocalDate date = DateTimeParser.deadlineDateParse("2022-01-01");
        String exp = "[D] [ ] return book (by: Jan 01 2022)";
        String act = new Deadline("return book", date).toString();
        assertEquals(exp, act);
    }

    @Test
    public void getDateTest() {
        LocalDate exp = DateTimeParser.deadlineDateParse("2022-01-01");
        LocalDate act = new Deadline("return book", DateTimeParser.deadlineDateParse("2022-01-01")).getDate();
        assertEquals(exp, act);
    }

    @Test
    public void getStatusTest() {
        Task deadline = new Deadline("return book", DateTimeParser.deadlineDateParse("2022-01-01"));
        deadline.markAsDone();
        assertTrue(deadline.getStatus());
    }
}
