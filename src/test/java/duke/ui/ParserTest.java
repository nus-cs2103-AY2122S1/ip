package duke.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ParserTest {
    @Test
    public void parse_normal_success() {
        String command = "todo homework";
        assertEquals(new UserInput(command, "todo", "homework"),
                Parser.parse(command));

        command = "event exam /at 2021-12-31";
        assertEquals(new UserInput(command, "event", "exam /at 2021-12-31"),
                Parser.parse(command));
    }

    @Test
    public void parse_withWhitespaces_success() {
        String command = "  todo homework  ";
        assertEquals(new UserInput(command, "todo", "homework"), Parser.parse(command));

        command = "  event    exam /at 2021-12-31  ";
        assertEquals(new UserInput(command, "event", "exam /at 2021-12-31"),
                Parser.parse(command));
    }
}
