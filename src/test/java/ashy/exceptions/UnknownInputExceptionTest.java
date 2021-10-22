package ashy.exceptions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class UnknownInputExceptionTest {
    @Test
    public void constructor_success() {
        assertEquals("Oh no! I'm sorry, I don't know what that means! ☹", new UnknownInputException().getMessage());
    }
}

