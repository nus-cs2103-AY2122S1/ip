package parser;

import command.*;
import org.junit.jupiter.api.Test;
import storage.Storage;
import task.TaskList;
import ui.Ui;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {
    private static final String STORAGE_FILEPATH = "data/tasks.txt";

    @Test
    public void parse_validCommands_commandsReturned() {
        String[] validCommands = {
                "list",
                "todo read book",
                "deadline return book /by 2021-10-15",
                "deadline return book /by 2021-10-15 18:00",
                "event book fest /at 2021-10-15",
                "event book fest /at 2021-10-15 18:00",
                "list",
                "done 3",
                "delete 5",
                "list",
                "bye"
        };

        TaskList tasks = new TaskList(new ArrayList<>());
        Ui ui = new Ui();
        Storage storage = new Storage(STORAGE_FILEPATH);

        for (String validCommand : validCommands) {
            assertDoesNotThrow(() -> {
                Command c = Parser.parse(validCommand);
                c.execute(tasks, ui, storage);
            });
        }
    }

    @Test
    public void parse_invalidCommands_exceptionThrown() {
        String[] invalidCommands = {
                "hello world",
                "todotodo",
                "listlist",
                "deadline return book",
                "deadline return book /by",
                "deadline return book /by 2021-18-06",
                "event book fest /at 2021-18-06",
                "done2",
                "delete3"
        };

        TaskList tasks = new TaskList(new ArrayList<>());
        Ui ui = new Ui();
        Storage storage = new Storage(STORAGE_FILEPATH);

        for (String command : invalidCommands) {
            assertThrows(exceptions.DukeException.class, () -> {
                Command c = Parser.parse(command);
                c.execute(tasks, ui, storage);
            });
        }
    }
}
