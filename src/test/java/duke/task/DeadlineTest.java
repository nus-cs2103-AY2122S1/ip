package duke.task;

import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void createNewDeadline() {
        assertEquals(new Deadline("pl1101e project",
                LocalDateTime.of(2021, 04, 20, 12,00)).displayInfo()
                , "[D] [ ] pl1101e project (by: 20 Apr 2021 12:00)");
    }
}
