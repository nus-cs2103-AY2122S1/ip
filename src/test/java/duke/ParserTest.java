package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.exception.DukeException;

public class ParserTest {
    @Test
    public void parseCompletetoDo() {
        assertEquals(Parser.parse("todo borrow book").getTask().getTaskName(),
                 "borrow book");
    }

    @Test
    public void parseIncompletetoDo() {
        try {
            Parser.parse("todo");
        } catch (DukeException e) {
            assertEquals(e.getMessage(), "Input in the format: todo *description*");
        }
    }

    @Test
    public void parseIncompleteEvent() {
        try {
            Parser.parse("event zoom meeting");
        } catch (DukeException e) {
            assertEquals(e.getMessage(),
                    "Input the following format: event *description* /at *DD/MM/YYYY* *24H-Time*");
        }
    }

    @Test
    public void parseCompleteDeadline() {
        assertEquals(Parser.parse("deadline PL1101E project /by 20/04/2021 1200".toLowerCase()).getTask().displayInfo(),
                "[D] [ ] pl1101e project (by: 20 Apr 2021 12:00)");
    }

    @Test
    public void parseCompleteDeadline_invalidDay() {
        try {
            Parser.parse("deadline PL1101E project /by 33/04/2021 1200");
        } catch (DukeException e) {
            assertEquals(e.getMessage(),
                    "Text '33/04/2021' could not be parsed: Invalid value for DayOfMonth (valid values 1 - 28/31): 33");
        }
    }

}
