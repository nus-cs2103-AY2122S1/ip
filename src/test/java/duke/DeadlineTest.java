package duke;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DeadlineTest {
    DateTimeHandler dth = new DateTimeHandler();
    Deadline d = new Deadline("test1", false, dth.parseDate("22/08/2021 1500"), new ArrayList<>());
    @Test
    public void testDeadline() {
        assertEquals(d.typeIcon(), "[D]");
        assertEquals(d.toString(), "[D] [-] test1 (by: 2021-08-22T15:00)");

        d.completeTask();
        assertEquals(d.toString(), "[D] [X] test1 (by: 2021-08-22T15:00)");
    }

}