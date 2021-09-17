package poseidon.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

import org.junit.jupiter.api.Test;

import poseidon.parser.Parser;

public class DeadlineTest {

    @Test
    public void toString_deadlineObject_stringRep() {
        LocalDateTime deadlineDT = Parser.parseDateTime("2021 08 30 2359");
        Deadline deadline = new Deadline("finish all tasks", deadlineDT);
        String expectedToString = "[D][ ] finish all tasks (by: "
                + deadlineDT.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM, FormatStyle.SHORT))
                + ")";
        assertEquals(expectedToString, deadline.toString());
    }

    @Test
    public void toStorage_deadlineObject_storageRep() {
        LocalDateTime deadlineDT = Parser.parseDateTime("2021 08 30 2359");
        Deadline deadline = new Deadline("finish all tasks", deadlineDT);
        assertEquals("D%false%finish all tasks%2021-08-30T23:59\n", deadline.toStorage());
    }
}
