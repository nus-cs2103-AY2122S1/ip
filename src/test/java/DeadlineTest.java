import org.junit.jupiter.api.Test;
import task.Deadline;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void testDateInput(){
        Deadline test = new Deadline("something", LocalDate.parse("2011-10-11"));
        assertEquals("[D][ ] something (by: Oct 11 2011)", test.toString());
    }
    @Test
    public void testMarkAsDone(){
        Deadline test = new Deadline("something", LocalDate.parse("2011-10-11"));
        test.markAsDone();
        assertEquals("[D][X] something (by: Oct 11 2011)", test.toString());
    }

    @Test
    public void testToData() {
        Deadline test = new Deadline("something", LocalDate.parse("2011-10-11"));
        test.markAsDone();
        assertEquals("D|1|something|2011-10-11\n", test.toData());
    }

    @Test
    public void testDateTimeInput() {
        Deadline test = new Deadline("something", LocalDateTime.parse("11-10-2019 1800", DateTimeFormatter.ofPattern("d-M-y HHmm")));
        test.markAsDone();
        assertEquals("[D][X] something (by: Oct 11 2019 1800)", test.toString());
    }

    @Test
    public void testYearMonthInput() {
        Deadline test = new Deadline("something", YearMonth.parse("11/19", DateTimeFormatter.ofPattern("M/y")));
        test.markAsDone();
        assertEquals("[D][X] something (by: Nov 0019)", test.toString());
    }
}
