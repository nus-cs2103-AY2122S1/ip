package botto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class DukeExceptionTest {

    @Test
    public void getMessage_normalMessage_success() {
        assertEquals("Sorry, I don't understand you.",
                new BottoException("Sorry, I don't understand you.").getMessage());
        assertEquals("What's your name.",
                new BottoException("What's your name.").getMessage());
        assertEquals("Wrong input format.",
                new BottoException("Wrong input format.").getMessage());
    }

}
