package task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.task.Deadline;

public class DeadlineTest {
    @Test
    public void toDataFormat() {
        Deadline deadline = new Deadline("Finish iP", "22 Aug 2021");
        assertEquals("D | 0 | Finish iP |  | 22 Aug 2021", deadline.toDataFormat());
    }

    @Test
    public void stringRepresentation() {
        Deadline deadline = new Deadline("Finish iP", "22 Aug 2021");
        assertEquals("[D][ ] Finish iP (by: 22 Aug 2021)", deadline.toString());
    }

    @Test
    public void dateParse() {
        Deadline deadline = new Deadline("Finish iP", "2021-11-12");
        assertEquals("D | 0 | Finish iP |  | Nov 12 2021", deadline.toDataFormat());
    }
}
