import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import duke.Deadline;


public class DeadlineTest {
    private Deadline testDeadline;

    public DeadlineTest() {
        this.testDeadline = new Deadline("Assignment", "23/09/2021");
    }

    @Test
    @DisplayName("Adding a new Deadline should work")
    public void testTodoToString() {
        assertEquals("[D][ ] Assignment (by: 23/09/2021)", testDeadline.toString());
    }
}
