package duke.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.Test;

public class DeadlineTest {
    @Test
    public void toString_stringReturned() {
        String des = "deadline submit assignment";
        LocalTime time = LocalTime.parse("20:00");
        LocalDate date = LocalDate.parse("2012-03-12");
        Deadline task = new Deadline(des, date, time);
        assertEquals("[D][ ] deadline submit assignment (by: 2012-03-12 20:00)", task.toString());
    }

    @Test
    public void formatString_stringReturned() {
        String des = "deadline submit assignment";
        LocalTime time = LocalTime.parse("20:00");
        LocalDate date = LocalDate.parse("2012-03-12");
        Deadline task = new Deadline(des, date, time);
        assertEquals("[D][ ] deadline submit assignment (by: Mar 12 2012 8:00PM)", task.formatString());
    }
}
