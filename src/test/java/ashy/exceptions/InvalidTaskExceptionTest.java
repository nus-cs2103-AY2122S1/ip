package ashy.exceptions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class InvalidTaskExceptionTest {
    @Test
    public void constructor_success() {
        assertEquals("Oh no! There is no such task in your to-do list! ☹", new InvalidTaskException().getMessage());
    }
}

