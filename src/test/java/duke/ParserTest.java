package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.command.DeleteCommand;
import duke.command.ListCommand;
import duke.exception.DukeException;

public class ParserTest {
    @Test
    void parser_deleteTask_deleteCommand() {
        try {
            assertEquals(new DeleteCommand(1), new Parser().parse("delete 2"));
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    void parser_list_listCommand() {
        try {
            assertEquals(new ListCommand(), new Parser().parse("list"));
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }
}
