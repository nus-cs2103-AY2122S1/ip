package duke.exceptions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskDoneAlreadyExceptionTest {
    @Test
    public void constructor_success() {
        assertEquals("You have completed this task already!", new TaskDoneAlreadyException().getMessage());
    }
}



