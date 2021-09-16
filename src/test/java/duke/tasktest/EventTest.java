package duke.tasktest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import duke.parser.DateTimeParser;
import duke.tasks.Event;
import duke.tasks.Task;

public class EventTest {

    @Test
    public void toStringTest() {
        LocalDateTime dateTime = DateTimeParser.eventDateTimeParse("2022-01-01 0800");
        String exp = "[E] [ ] return book (at: Jan 01 2022 0800)";
        String act = new Event("return book", dateTime).toString();
        assertEquals(exp, act);
    }

    @Test
    public void getDateTest() {
        LocalDate exp = DateTimeParser.deadlineDateParse("2022-01-01");
        LocalDate act = new Event("return book", DateTimeParser.eventDateTimeParse("2022-01-01 2359")).getDate();
        assertEquals(exp, act);
    }

    @Test
    public void getStatusTest() {
        Task event = new Event("return book", DateTimeParser.eventDateTimeParse("2022-01-01 0000"));
        event.markAsDone();
        assertTrue(event.getStatus());
    }


}
