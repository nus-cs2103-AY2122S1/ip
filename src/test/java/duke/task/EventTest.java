package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.Test;

class EventTest {

    @Test
    void checkDate1() {
        Event task = new Event("CCA", LocalDate.parse("2021-08-23"), LocalTime.parse("20:00"),
                LocalTime.parse("23:00"));
        assertEquals(true, task.hasDate(LocalDate.parse("2021-08-23")));
    }

    @Test
    void checkDate2() {
        Event task = new Event("CCA", LocalDate.parse("2021-08-23"), LocalTime.parse("20:00"),
                LocalTime.parse("23:00"));
        assertEquals(false, task.hasDate(LocalDate.parse("2021-08-24")));
    }

    @Test
    void testToString() {
        Event task = new Event("CCA", LocalDate.parse("2021-08-23"), LocalTime.parse("20:00"),
                LocalTime.parse("23:00"));
        assertEquals("[E][ ] CCA (at: Aug 23 2021 20:00-23:00)", task.toString());
    }

    @Test
    void format1() {
        Event task = new Event("CCA", LocalDate.parse("2021-08-23"), LocalTime.parse("20:00"),
                LocalTime.parse("23:00"));
        assertEquals("E | 0 | CCA | 2021-08-23 20:00-23:00", task.format());
    }

    @Test
    void format2() {
        Event task = new Event("CCA", LocalDate.parse("2021-08-23"), LocalTime.parse("20:00"),
                LocalTime.parse("23:00"));
        task.markDone();
        assertEquals("E | 1 | CCA | 2021-08-23 20:00-23:00", task.format());
    }

    @Test
    void testMarkDone() {
        Event task = new Event("CCA", LocalDate.parse("2021-08-23"), LocalTime.parse("20:00"),
                LocalTime.parse("23:00"));
        task.markDone();
        assertEquals("[E][X] CCA (at: Aug 23 2021 20:00-23:00)", task.toString());
    }

    @Test
    void testGetStatusIcon() {
        Event task = new Event("CCA", LocalDate.parse("2021-08-23"), LocalTime.parse("20:00"),
                LocalTime.parse("23:00"));
        task.markDone();
        assertEquals("X", task.getStatusIcon());
    }
}
