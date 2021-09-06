import duke.tasks.Deadline;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    protected static final DateTimeFormatter formatted = DateTimeFormatter.ofPattern("MMM d yyyy HHmm");

    @Test
    public void createDeadlineTest(){
        LocalDateTime startTime = LocalDateTime.parse("10-10-1010 1010", formatted);
        Deadline deadline = new Deadline("deadline helloworld/by 10-10-1010 1010", startTime);
        assertEquals("[D][ ] helloworld (by: Oct 10 1010 1010)", deadline.toString());
    }

    @Test
    public void completeDeadlineTest() {
        LocalDateTime startTime = LocalDateTime.parse("10-10-1010 1010", formatted);
        Deadline deadline = new Deadline("deadline helloworld/by 10-10-1010 1010", startTime);
        deadline.markAsDone();
        assertEquals("[D][X] helloworld (by: Oct 10 1010 1010)", deadline.toString());
    }
}