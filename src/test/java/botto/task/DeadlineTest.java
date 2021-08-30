package botto.task;


import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

public class DeadlineTest {
    @Test
    public void testStringConversion() {
        Deadline task1 = new Deadline("read book", LocalDateTime.of(2021, 7, 15, 4, 30));
        assertEquals("[D][ ] read book (by: 15/7/2021 4:30 am)", task1.toString());
        Deadline task2 = new Deadline("return book", LocalDateTime.of(2021, 12, 3, 15, 30));
        assertEquals("[D][ ] return book (by: 3/12/2021 3:30 pm)", task2.toString());
    }
}
