package duke.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class UnknownCommandExceptionTest {
    @Test
    public void constructor_success() {
        assertEquals("Sorry but my database does not have such command.",
            new UnknownCommandException().getMessage());
    }
}
