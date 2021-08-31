package sora.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class SoraExceptionTest {
    @Test
    public void constructor_success() {
        assertEquals("test message", new SoraException("test message").getMessage());
    }
}
