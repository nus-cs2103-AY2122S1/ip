package sora.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class EmptyResultExceptionTest {
    @Test
    public void constructor_success() {
        assertEquals("I found nothing... Maybe try another keyword?",
                new EmptyResultException().getMessage());
    }
}
