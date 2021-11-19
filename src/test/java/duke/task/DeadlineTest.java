package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class DeadlineTest {

    @Test
    void getAdditionalInfo() {
        Deadline d = new Deadline("deadline", "25 Jul 2021 04:30 PM");
        assertEquals("25 Jul 2021 04:30 PM", d.getAdditionalInfo());
    }

    @Test
    void getTag() {
        Deadline d = new Deadline("a", "b");
        assertEquals("D", d.getTag());
    }

    @Test
    void testToString() {
        Deadline d = new Deadline("CS2103T assignment", "25 Jul 2021 04:30 PM");
        assertEquals("[D][ ] CS2103T assignment (by: 25 Jul 2021 04:30 PM)", d.toString());
    }

    @Test
    void testToStringDone() {
        Deadline d = new Deadline("CS2103T assignment", "25 Jul 2021 04:30 PM");
        d.setDone();
        assertEquals("[D][X] CS2103T assignment (by: 25 Jul 2021 04:30 PM)", d.toString());
    }
}
