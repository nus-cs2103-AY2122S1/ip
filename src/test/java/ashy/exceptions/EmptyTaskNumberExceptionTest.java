package ashy.exceptions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class EmptyTaskNumberExceptionTest {
    @Test
    public void constructor_success() {
        assertEquals("Oh no! You haven't mentioned the task number! ☹", new EmptyTaskNumberException().getMessage());
    }
}

