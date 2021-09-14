package botto.util;


import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import botto.BottoException;
import botto.command.AddDeadlineCommand;
import botto.command.AddEventCommand;
import botto.command.AddToDoCommand;
import botto.command.DeleteCommand;
import botto.command.ExitCommand;
import botto.command.FindCommand;
import botto.command.HelpCommand;
import botto.command.MarkDoneCommand;
import botto.command.ShowListCommand;

public class ParserTest {

    @Test
    public void parse_allCommands_success() throws BottoException {
        String command = "list";
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
        command = "find book";
        assertEquals(FindCommand.class, Parser.parse(command).getClass());
        command = "help";
        assertEquals(HelpCommand.class, Parser.parse(command).getClass());
    }

    @Test
    public void parse_undefinedCommands_exceptionThrown() {
        try {
            String command = "";
            Parser.parse(command);
        } catch (BottoException e) {
            assertEquals("☹ OOPS!!! I'm sorry, but I don't know what that means :-(", e.getMessage());
        }

        try {
            String command = "I want a bowl";
            Parser.parse(command);
        } catch (BottoException e) {
            assertEquals("☹ OOPS!!! I'm sorry, but I don't know what that means :-(", e.getMessage());
        }
    }

}
