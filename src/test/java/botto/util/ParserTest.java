package botto.util;

import botto.DukeException;
import botto.command.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {

    @Test
    public void parse_allCommands_success() throws DukeException {
        String command = "list     ";
        assertEquals(ShowListCommand.class, Parser.parse(command).getClass());
        command = "done 5";
        assertEquals(MarkDoneCommand.class, Parser.parse(command).getClass());
        command = "todo read book";
        assertEquals(AddToDoCommand.class, Parser.parse(command).getClass());
        command = "deadline return book /by 31/8/2021 3:00 pm";
        assertEquals(AddDeadlineCommand.class, Parser.parse(command).getClass());
        command = "event birthday party /at 30/9/2021 6:00 pm";
        assertEquals(AddEventCommand.class, Parser.parse(command).getClass());
        command = "delete 19";
        assertEquals(DeleteCommand.class, Parser.parse(command).getClass());
        command = "bye";
        assertEquals(ExitCommand.class, Parser.parse(command).getClass());
    }

    @Test
    public void parse_undefinedCommands_exceptionThrown() {
        try {
            String command = "";
            assertEquals(ShowListCommand.class, Parser.parse(command).getClass());
        } catch (DukeException e) {
            assertEquals("☹ OOPS!!! I'm sorry, but I don't know what that means :-(", e.getMessage());
        }

        try {
            String command = "I want a bowl";
            assertEquals(DeleteCommand.class, Parser.parse(command).getClass());
        } catch (DukeException e) {
            assertEquals("☹ OOPS!!! I'm sorry, but I don't know what that means :-(", e.getMessage());
        }
    }

}
