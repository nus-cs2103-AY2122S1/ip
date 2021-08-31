package duke.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class AddTodoCommandTest {

    @Test
    public void testValidTodoCommand1() {
        String expected = "TO ADD: [T][ ] PLACEHOLDER SENTENCE";
        Command actual = new AddTodoCommand(new String[] {"PLACEHOLDER SENTENCE"});
        assertEquals(
                expected,
                actual.toString()
        );
    }

    @Test
    public void testValidTodoCommand2() {
        String expected = "TO ADD: [T][ ] PLACEHOLDER /at sometime";
        Command actual = new AddTodoCommand(new String[] {"PLACEHOLDER /at sometime"});
        assertEquals(
                expected,
                actual.toString()
        );
    }
}
