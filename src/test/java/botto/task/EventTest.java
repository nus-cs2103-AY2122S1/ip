package botto.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import org.junit.jupiter.api.Test;

public class EventTest {
    @Test
    public void testStringConversion() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy h:mm a", Locale.getDefault());
        LocalDateTime localDateTime1 = LocalDateTime.of(2021, 7, 15, 4, 30);
        Event task1 = new Event("read book", localDateTime1);
        assertEquals("[E][ ] read book (at: " + formatter.format(localDateTime1) + ")", task1.toString());

        LocalDateTime localDateTime2 = LocalDateTime.of(2021, 12, 3, 15, 30);
        Event task2 = new Event("return book", localDateTime2);
        assertEquals("[E][ ] return book (at: " + formatter.format(localDateTime2) + ")", task2.toString());
    }
}
