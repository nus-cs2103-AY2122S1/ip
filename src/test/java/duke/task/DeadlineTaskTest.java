package duke.task;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;


public class DeadlineTaskTest {
    @Test
    public void getDeadlineTest() {
        assertEquals(LocalDateTime.of(2000, 8, 25, 23, 59)
                .format(DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm")),
                        new DeadlineTask("return a book", "25/08/2000 2359").getDeadline());
    }

    @Test
    public void writeToFileTest() {
        assertEquals("DEADLINE | 0 | return a book | 25/08/2000 2359\n",
                new DeadlineTask("return a book", "25/08/2000 2359").writeToFile());
    }

    @Test
    public void toStringTest() {
        assertEquals("[D][ ] return a book (by: Aug 25 2000, 23:59)",
                new DeadlineTask("return a book", "25/08/2000 2359").toString());
    }
}
