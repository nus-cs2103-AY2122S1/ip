package sora.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class IllegalFormatExceptionTest {
    @Test
    public void constructor_success() {
        assertEquals("Please follow this format:\n  test format",
                new IllegalFormatException("test format").getMessage());
    }
}
