package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.Test;

class DeadlineTest {

    @Test
    void checkDate1() {
        Deadline task = new Deadline("return book", LocalDate.parse("2021-08-23"), LocalTime.parse("20:00"));
        assertEquals(true, task.hasDate(LocalDate.parse("2021-08-23")));
    }

    @Test
    void checkDate2() {
        Deadline task = new Deadline("return book", LocalDate.parse("2021-08-23"), LocalTime.parse("20:00"));
        assertEquals(false, task.hasDate(LocalDate.parse("2021-08-24")));
    }

    @Test
    void testToString() {
        Deadline task = new Deadline("return book", LocalDate.parse("2021-08-23"), LocalTime.parse("20:00"));
        assertEquals("[D][ ] return book (by: Aug 23 2021 20:00)", task.toString());
    }

    @Test
    void format1() {
        Deadline task = new Deadline("return book", LocalDate.parse("2021-08-23"), LocalTime.parse("20:00"));
        assertEquals("D | 0 | return book | 2021-08-23 20:00", task.format());
    }

    @Test
    void format2() {
        Deadline task = new Deadline("return book", LocalDate.parse("2021-08-23"), LocalTime.parse("20:00"));
        task.markDone();
        assertEquals("D | 1 | return book | 2021-08-23 20:00", task.format());
    }

    @Test
    void testMarkDone() {
        Deadline task = new Deadline("return book", LocalDate.parse("2021-08-23"), LocalTime.parse("20:00"));
        task.markDone();
        assertEquals("[D][X] return book (by: Aug 23 2021 20:00)", task.toString());
    }

    @Test
    void testGetStatusIcon() {
        Deadline task = new Deadline("return book", LocalDate.parse("2021-08-23"), LocalTime.parse("20:00"));
        task.markDone();
        assertEquals("X", task.getStatusIcon());
    }
}
