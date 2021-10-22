package ashy.exceptions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TaskDoneAlreadyExceptionTest {
    @Test
    public void constructor_success() {
        assertEquals("Oh no! You have completed this task already!", new TaskDoneAlreadyException().getMessage());
    }
}



