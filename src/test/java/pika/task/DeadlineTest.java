package pika.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import pika.exception.PikaException;

public class DeadlineTest {

    @Test
    public void test1() throws PikaException {
        assertEquals("[D][ ] Test Deadline (by: Oct 10 2020 0500PM)",
                new Deadline("Test Deadline", "2020-10-10 17:00").toString());
    }

    @Test
    public void test2() throws PikaException {
        assertEquals("D | 0 | Test Deadline | Oct 10 2020",
                new Deadline("Test Deadline", "2020-10-10").write());
    }

    @Test
    public void test3() throws PikaException {
        assertEquals("[D][X] Test Deadline (by: Nov 10 2021 0900PM)" ,
                new Deadline("Test Deadline", "2021-11-10 21:00").markAsDone());
    }
}
