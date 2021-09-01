package pika.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TaskTimeTest {

    @Test
    public void testA1() {
        assertEquals("Oct 10 2020", TaskTime.convertDateTimeFormat("2020-10-10"));
    }

    @Test
    public void testA2() {
        assertEquals("Oct 10 2020 0600PM", TaskTime.convertDateTimeFormat("2020-10-10 18:00"));
    }

    @Test
    public void testB1() {
        assertEquals("2021-10-10", TaskTime.unconvertDateTime("Oct 10 2021"));
    }

    @Test
    public void testB2() {
        assertEquals("2020-10-10 17:00", TaskTime.unconvertDateTime("Oct 10 2020 0500PM"));
    }
}
