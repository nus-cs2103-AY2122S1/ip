package ashy.exceptions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class NoTimeExceptionTest {
    @Test
    public void constructor_success() {
        assertEquals("Oh no! Please provide a timeline for your task! ☹", new NoTimeException().getMessage());
    }
}


