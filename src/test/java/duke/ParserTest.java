package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    @Test
    void parse() {
        try {
            assertEquals(new DeleteCommand(1), new Parser(" ").parse("delete 2"));
        } catch (DukeException e){
            System.out.println(e.getMessage());
        }
    }
}
