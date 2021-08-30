package exceptions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.exception.MessageEmptyException;

public class MessageEmptyExceptionTest {
    @Test
    public void testExceptionMessage() {
        assertEquals("You forgot to enter a message after the command!",
                new MessageEmptyException().getMessage());
    }
}
