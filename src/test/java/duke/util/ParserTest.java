package duke.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import duke.task.Task;

class ParserTest {

    @Test
    void isExit_success() {
        ArrayList<Task> tasks = new ArrayList<>();
        TaskList taskList = new TaskList(tasks);
        Ui ui = new Ui();
        Storage storage = new Storage("data/test.txt");
        Parser parser = new Parser(taskList, ui, storage);

        parser.parse("bye");
        assertEquals(true, parser.isExit());
    }

    @Test
    void parse_validInput_success() {
        try {
            ArrayList<Task> tasks = new ArrayList<>();
            TaskList taskList = new TaskList(tasks);
            Ui ui = new Ui();
            Storage storage = new Storage("data/test.txt");
            Parser parser = new Parser(taskList, ui, storage);

            parser.parse("find test");
            parser.parse("event meeting /at 21/10/2021");
            parser.parse("done 1");
            parser.parse("todo buy milk");
            parser.parse("delete 1");
        } catch (Exception e) {
            fail();
        }

    }

    @Test
    void parse_invalidInput_errorThrow() {
        try {
            ArrayList<Task> tasks = new ArrayList<>();
            TaskList taskList = new TaskList(tasks);
            Ui ui = new Ui();
            Storage storage = new Storage("data/test.txt");
            Parser parser = new Parser(taskList, ui, storage);

            parser.parse("exit");
            fail();
        } catch (Exception e) {
            assertTrue(e.getMessage().contains("OOPS!!! I'm sorry, but I don't know what that means :-("));
        }
    }
}
