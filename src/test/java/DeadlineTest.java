import static duke.common.Formats.DT_INPUT_FORMAT;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import duke.tasks.Deadline;

public class DeadlineTest {
    @Test
    public void outputTest1() {
        Deadline testDeadline = new Deadline(
                false, "homework", LocalDateTime.parse("05/10/2021 6:00AM", DT_INPUT_FORMAT));
        assertEquals("[D][ ] :  homework (by: Oct 5 2021 6:00AM)", testDeadline.toString());
    }

    @Test
    public void outputTest2() {
        Deadline testDeadline = new Deadline(
                false, "homework", LocalDateTime.parse("05/10/2021 6:00AM", DT_INPUT_FORMAT));
        testDeadline.markAsDone();
        assertEquals("[D][X] :  homework (by: Oct 5 2021 6:00AM)", testDeadline.toString());
    }
}
