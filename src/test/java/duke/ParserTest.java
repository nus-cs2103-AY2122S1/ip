package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.exception.DukeException;

public class ParserTest {
    @Test
    public void parse_emptyDoneKeyword_exceptionThrown() {
        String testInput = "done";
        try {
            Parser.parse(testInput);
        } catch (DukeException e) {
            assertEquals(e.toString(), "OOPS!!! Please enter the task you'd like to"
                    + " mark as done in the following format: \n\t done [task number]");
        }
    }

    @Test
    public void parse_emptyDeleteKeyword_exceptionThrown() {
        String testInput = "delete";
        try {
            Parser.parse(testInput);
        } catch (DukeException e) {
            assertEquals(e.toString(), "OOPS!!! Please enter the task you'd like to"
                    + " delete in the following format: \n\t delete [task number]");
        }
    }
}
