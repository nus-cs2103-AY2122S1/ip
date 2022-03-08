package duke.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import duke.command.AddTaskCommand;
import duke.command.ListCommand;
import duke.exception.DukeException;
import duke.task.Deadline;

public class ParserTest {
    @Test
    void testParseRawInputList() {
        try {
            Parser parser = new Parser();
            String testInput = "list";
            assertEquals(new ListCommand(), parser.parseRawInput(testInput));
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    void testParseRawInputDeadline() {
        try {
            Parser parser = new Parser();
            String testInput = "deadline ip tasks /by 2021-12-26 2359";
            assertEquals(new AddTaskCommand(new Deadline("ip tasks", "26 Dec 2021, 11.59 pm")),
                    parser.parseRawInput(testInput));
        } catch (DukeException e) {
            fail();
        }
    }
}
