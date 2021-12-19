package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;


public class EventTaskTest {
    @Test
    public void getDetailsTest() {
        assertEquals(LocalDateTime.of(2000, 8, 25, 23, 59)
                        .format(DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm")),
                new EventTask("return a book", "25/08/2000 2359").getDetails());
    }

    @Test
    public void writeToFileTest() {
        assertEquals("EVENT | 0 | return a book | 25/08/2000 2359\n",
                new EventTask("return a book", "25/08/2000 2359").writeToFile());
    }

    @Test
    public void toStringTest() {
        assertEquals("[E][ ] return a book (at: Aug 25 2000, 23:59)",
                new EventTask("return a book", "25/08/2000 2359").toString());
    }
}
