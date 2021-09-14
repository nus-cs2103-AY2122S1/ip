package botto.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class EventTest {
    private DateTimeFormatter formatter;

    @BeforeEach
    public void setUp() {
        formatter = DateTimeFormatter.ofPattern("d/M/yyyy h:mm a", Locale.getDefault());
    }

    @Test
    public void testStringConversion() {
        LocalDateTime localDateTime1 = LocalDateTime.of(2021, 7, 15, 4, 30);
        Event task1 = new Event("read book", localDateTime1);
        assertEquals("[E][ ] read book (at: " + formatter.format(localDateTime1) + ")", task1.toString());

        LocalDateTime localDateTime2 = LocalDateTime.of(2021, 12, 3, 15, 30);
        Event task2 = new Event("return book", localDateTime2);
        assertEquals("[E][ ] return book (at: " + formatter.format(localDateTime2) + ")", task2.toString());
    }

    @Test
    public void compareTo_events_success() {
        LocalDateTime localDateTime1 = LocalDateTime.of(2021, 7, 15, 4, 30);
        LocalDateTime localDateTime2 = LocalDateTime.of(2021, 12, 3, 15, 30);
        LocalDateTime localDateTime3 = LocalDateTime.of(2022, 1, 31, 2, 0);

        Event task1 = new Event("read book", localDateTime1);
        Event task2 = new Event("return book", localDateTime2);
        Event task3 = new Event("borrow book", localDateTime3);

        assertTrue(task1.compareTo(task2) < 0);
        assertTrue(task2.compareTo(task1) > 0);
        assertTrue(task3.compareTo(task2) > 0);
        assertTrue(task3.compareTo(task1) > 0);
    }

    @AfterEach
    public void tearDown() {
        formatter = null;
    }
}
