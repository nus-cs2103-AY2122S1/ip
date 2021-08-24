import org.junit.jupiter.api.Test;
import task.Deadline;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void testInput(){
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
}
