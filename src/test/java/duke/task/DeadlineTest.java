package duke.task;

import java.time.LocalDate;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void testDeadline() {
        LocalDate by = LocalDate.parse("2021-08-30");
        Deadline deadline = new Deadline("return book", by);
        assertEquals("[D][ ] return book (by: 08 30 2021)", deadline.toString());
    }
}
