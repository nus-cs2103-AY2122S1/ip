package jackie.command;

import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

import jackie.JackieException;

public class AddCommandTest {

    @Test
    public void isExit_anyInput_false() throws JackieException {
        assertFalse(new AddCommand("todo holder".split(" ")).isExit());
    }
}
