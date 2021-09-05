package botto.task;


import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import org.junit.jupiter.api.Test;

public class DeadlineTest {
    @Test
    public void testStringConversion() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy h:mm a", Locale.getDefault());
        LocalDateTime localDateTime1 = LocalDateTime.of(2021, 7, 15, 4, 30);
        Deadline task1 = new Deadline("read book", localDateTime1);
        assertEquals("[D][ ] read book (by: " + formatter.format(localDateTime1) + ")", task1.toString());

        LocalDateTime localDateTime2 = LocalDateTime.of(2021, 12, 3, 15, 30);
        Deadline task2 = new Deadline("return book", localDateTime2);
        assertEquals("[D][ ] return book (by: " + formatter.format(localDateTime2) + ")", task2.toString());
    }
}
