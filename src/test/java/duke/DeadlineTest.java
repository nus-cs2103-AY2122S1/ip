package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import duke.task.Deadline;

public class DeadlineTest {
    @Test
    public void deadline_toString_success() {
        assertEquals("[D][ ] assignment (by: Aug 29 2021)",
                new Deadline("0", "assignment", "2021-08-29").toString());
    }
    
}
