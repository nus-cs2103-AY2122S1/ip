package duke;

import duke.command.AddCommand;
import duke.command.DoneCommand;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    @Test
    public void addTodoCommandTest() throws DukeException {
        assertEquals(new AddCommand("todo return book", TaskEnum.TODO),
                Parser.parse("todo return book"));
    }

    @Test
    public void doneCommandTest() throws DukeException {
        assertEquals(new DoneCommand("done 2"),
                Parser.parse("done 2"));
    }
}
