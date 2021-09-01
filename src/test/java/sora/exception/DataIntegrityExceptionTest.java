package sora.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class DataIntegrityExceptionTest {
    @Test
    public void constructor_success() {
        assertEquals("Storage file integrity compromised :(",
                new DataIntegrityException().getMessage());
    }
}
