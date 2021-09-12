import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import taubot.Deadline;



class DeadlineTest {

    final String date = "2000-10-10";
    final Deadline dl = new Deadline("homework", LocalDate.parse(date), "1000");

    @Test
    void getBy() {
        assertEquals("2000-10-10 1000", dl.getBy());
    }

    @Test
    void testToString() {
        assertEquals("[D][ ] homework (by: 10 Oct 2000 1000)", dl.toString());
    }
}
