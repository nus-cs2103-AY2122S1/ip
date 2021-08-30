package botto.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

public class EventTest {
    @Test
    public void testStringConversion() {
        Event task1 = new Event("read book", LocalDateTime.of(2021, 7, 15, 4, 30));
        assertEquals("[E][ ] read book (at: 15/7/2021 4:30 am)", task1.toString());
        Event task2 = new Event("return book", LocalDateTime.of(2021, 12, 3, 15, 30));
        assertEquals("[E][ ] return book (at: 3/12/2021 3:30 pm)", task2.toString());
    }
}
