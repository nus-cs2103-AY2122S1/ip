package botto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DukeExceptionTest {

    @Test
    public void getMessage_normalMessage_success() {
        assertEquals("Sorry, I don't understand you.",
                new DukeException("Sorry, I don't understand you.").getMessage());
        assertEquals("What's your name.",
                new DukeException("What's your name.").getMessage());
        assertEquals("Wrong input format.",
                new DukeException("Wrong input format.").getMessage());
    }

}
