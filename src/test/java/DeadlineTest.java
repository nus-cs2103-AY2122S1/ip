import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

import iris.task.Deadline;

public class DeadlineTest {

    @Test
    public void deadlineGetDeadlineTime() {
        String commandDetails = "return book /by 2021-08-31 15:00";
        int byIndex = commandDetails.indexOf("/by") - 1;
        String deadlineDetails = commandDetails.substring(0, byIndex);
        LocalDateTime by = LocalDateTime.parse(commandDetails.substring(commandDetails.indexOf("by") + 3),
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        Deadline deadline = new Deadline(deadlineDetails, false, by);
        assertEquals("(by: 31-08-2021 15:00)", deadline.getDeadlineTime());
    }

    @Test
    public void deadlineGetStatus() {
        String commandDetails = "return book /by 2021-08-31 15:00";
        int byIndex = commandDetails.indexOf("/by") - 1;
        String deadlineDetails = commandDetails.substring(0, byIndex);
        LocalDateTime by = LocalDateTime.parse(commandDetails.substring(commandDetails.indexOf("by") + 3),
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        Deadline deadline = new Deadline(deadlineDetails, false, by);
        assertEquals("[D][ ] return book (by: 31-08-2021 15:00)", deadline.getStatus());
        deadline.markDone();
        assertEquals("[D][X] return book (by: 31-08-2021 15:00)", deadline.getStatus());
    }

    @Test
    public void deadlineToString() {
        String commandDetails = "return book /by 2021-08-31 15:00";
        int byIndex = commandDetails.indexOf("/by") - 1;
        String deadlineDetails = commandDetails.substring(0, byIndex);
        LocalDateTime by = LocalDateTime.parse(commandDetails.substring(commandDetails.indexOf("by") + 3),
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        Deadline deadline = new Deadline(deadlineDetails, false, by);
        assertEquals("D | 0 | return book | 2021-08-31T15:00", deadline.toString());
        deadline.markDone();
        assertEquals("D | 1 | return book | 2021-08-31T15:00", deadline.toString());
    }
}
