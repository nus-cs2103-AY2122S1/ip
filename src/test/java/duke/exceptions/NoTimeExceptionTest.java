package duke.exceptions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NoTimeExceptionTest {
    @Test
    public void constructor_success() {
        assertEquals("Please provide a timeline for your task! ☹", new NoTimeException().getMessage());
    }
}


