package duke.exceptions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EmptyTaskNumberExceptionTest {
    @Test
    public void constructor_success() {
        assertEquals("You haven't mentioned the task number! ☹", new EmptyTaskNumberException().getMessage());
    }
}

