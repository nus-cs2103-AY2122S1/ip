package poseidon.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import poseidon.Parser;

public class DeadlineTest {

    @Test
    public void toString_deadlineObject_stringRep() {
        LocalDateTime deadlineDT = Parser.parseDateTime("2021 08 30 2359");
        Deadline deadline = new Deadline("finish all tasks", deadlineDT);
        assertEquals("[D][ ] finish all tasks (by: 30 Aug 2021, 11:59 PM)", deadline.toString());
    }

    @Test
    public void toStorage_deadlineObject_storageRep() {
        LocalDateTime deadlineDT = Parser.parseDateTime("2021 08 30 2359");
        Deadline deadline = new Deadline("finish all tasks", deadlineDT);
        assertEquals("D%false%finish all tasks%2021-08-30T23:59\n", deadline.toStorage());
    }
}
