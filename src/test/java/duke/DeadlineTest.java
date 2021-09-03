package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

class DeadlineTest {

    @Test
    void getDeadline() {
        LocalDate date = LocalDate.of(2021, 8, 22);
        Deadline deadline = new Deadline(" Read Book", date);
        assertEquals("2021-08-22", deadline.getDeadline().toString());
    }

    @Test
    void setDone() {
        LocalDate date = LocalDate.of(2021, 8, 22);
        Deadline deadline = new Deadline(" Read Book", date);
        Deadline deadlineDone = new Deadline(" Read Book", true, date);
        assertEquals("[D] [ ] Read Book (by: Aug 22 2021)", deadline.toString());
        assertEquals(deadlineDone.toString(), deadline.setDone().toString());
    }

    @Test
    void testToString() {
        LocalDate date = LocalDate.of(2021, 8, 22);
        Deadline deadline = new Deadline(" Read Book", date);
        Deadline deadlineDone = new Deadline(" Read Book", true, date);
        assertEquals("[D] [ ] Read Book (by: Aug 22 2021)", deadline.toString());
        assertEquals("[D] [X] Read Book (by: Aug 22 2021)", deadlineDone.toString());
    }
}
