package sora.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class UnknownCommandExceptionTest {
    @Test
    public void constructor_success() {
        assertEquals("Sorry but my database does not have such command.\n"
                        + "Try typing 'help' for more information regarding this app!",
                new UnknownCommandException().getMessage());
    }
}
