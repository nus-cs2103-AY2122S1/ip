package sora.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class EmptyListExceptionTest {
    @Test
    public void constructor_success() {
        assertEquals("Your list is empty! Maybe add some tasks into it?",
                new EmptyListException().getMessage());
    }
}
