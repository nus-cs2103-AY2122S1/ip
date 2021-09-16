package duke.tasktest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import duke.parser.DateTimeParser;
import duke.tasks.Deadline;
import duke.tasks.Task;


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
