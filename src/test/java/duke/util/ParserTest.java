package duke.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import duke.command.AddTaskCommand;
import duke.command.ListCommand;
import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;

public class ParserTest {
    @Test
    void testParseRawInputValid() {
        try {
            Parser parser = new Parser();
            String testInput = "list";
            assertEquals(new ListCommand(), parser.parseRawInput(testInput));
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    void testParseRawInputValid1() {
        try {
            Parser parser = new Parser();
            String testInput = "todo ip tasks";
            assertEquals(new AddTaskCommand(new Todo("ip tasks")),
                    parser.parseRawInput(testInput));
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    void testParseRawInputValid2() {
        try {
            Parser parser = new Parser();
            String testInput = "deadline ip tasks /by 2021-12-26 2359";
            assertEquals(new AddTaskCommand(new Deadline("ip tasks", "26 Dec 2021, 11.59 pm")),
                    parser.parseRawInput(testInput));
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    void testParseRawInputValid3() {
        try {
            Parser parser = new Parser();
            String testInput = "event volleyball training /at wed 6pm";
            assertEquals(new AddTaskCommand(new Event("volleyball training", "wed 6pm")),
                    parser.parseRawInput(testInput));
        } catch (DukeException e) {
            fail();
        }
    }
}
