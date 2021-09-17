package poseidon.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

import org.junit.jupiter.api.Test;

import poseidon.parser.Parser;

/**
 * Represents a testing class for {@code Deadline}.
 *
 * @author Yeluri Ketan
 * @version CS2103T AY21/22 Sem 1 iP
 */
public class DeadlineTest {

    @Test
    public void deadlineConstructor_newDeadline_correctDescAndStatus() {
        String description = "Finish all tasks";
        LocalDateTime deadlineDT = Parser.parseDateTime("2021 08 30 2359");
        Deadline deadline = new Deadline(description, deadlineDT);
        assertEquals(description, deadline.description);
        assertFalse(deadline.isDone);
    }

    @Test
    public void deadlineConstructorWithStatus_undoneDeadline_correctDoneStatus() {
        String description = "Finish all tasks";
        LocalDateTime deadlineDT = Parser.parseDateTime("2021 08 30 2359");
        Deadline deadline = new Deadline(description, false, deadlineDT);
        assertFalse(deadline.isDone);
    }

    @Test
    public void deadlineConstructorWithStatus_doneDeadline_correctDoneStatus() {
        String description = "Finish all tasks";
        LocalDateTime deadlineDT = Parser.parseDateTime("2021 08 30 2359");
        Deadline deadline = new Deadline(description, true, deadlineDT);
        assertTrue(deadline.isDone);
    }

    @Test
    public void setDone_deadlineMarkedDone_correctDoneStatus() {
        String description = "Finish all tasks";
        LocalDateTime deadlineDT = Parser.parseDateTime("2021 08 30 2359");
        Deadline deadline = new Deadline(description, deadlineDT);
        deadline.setDone();
        assertTrue(deadline.isDone);
    }

    @Test
    public void getDateTime_deadlineObject_correctDateTime() {
        String description = "Finish all tasks";
        LocalDateTime deadlineDT = Parser.parseDateTime("2021 08 30 2359");
        Deadline deadline1 = new Deadline(description, deadlineDT);
        assertEquals(deadlineDT, deadline1.getDateTime());

        Deadline deadline2 = new Deadline(description, true, deadlineDT);
        assertEquals(deadlineDT, deadline2.getDateTime());

        Deadline deadline3 = new Deadline(description, false, deadlineDT);
        assertEquals(deadlineDT, deadline3.getDateTime());
    }

    @Test
    public void toString_deadlineObject_stringRep() {
        String description = "Finish all tasks";
        LocalDateTime deadlineDT = Parser.parseDateTime("2021 08 30 2359");
        Deadline deadline = new Deadline(description, deadlineDT);
        String expectedToString = "[D][ ] Finish all tasks (by: "
                + deadlineDT.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM, FormatStyle.SHORT))
                + ")";
        assertEquals(expectedToString, deadline.toString());
    }

    @Test
    public void toStorage_deadlineObject_storageRep() {
        String description = "Finish all tasks";
        LocalDateTime deadlineDT = Parser.parseDateTime("2021 08 30 2359");
        Deadline deadline = new Deadline(description, deadlineDT);
        String expectedToStorageString = "D%false%Finish all tasks%2021-08-30T23:59\n";
        assertEquals(expectedToStorageString, deadline.toStorage());
    }
}
