package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.exception.DukeException;

public class ParserTest {

    @Test
    public void execute_doneMissingNumber_exceptionThrown() {
        ChatBot bot = new ChatBot();
        Parser p = new Parser();
        try {
            p.parse("done", bot);
        } catch (DukeException e) {
            assertEquals("The task number cannot be empty you dum dum", e.getMessage());
        }
    }

    @Test
    public void execute_doneInvalidNumber_exceptionThrown() {
        ChatBot bot = new ChatBot();
        Parser p = new Parser();
        try {
            p.parse("done -1", bot);
        } catch (DukeException e) {
            assertEquals("Please enter a valid number", e.getMessage());
        }
    }

    @Test
    public void execute_deadlineMissingDescription_exceptionThrown() {
        ChatBot bot = new ChatBot();
        Parser p = new Parser();
        try {
            p.parse("deadline", bot);
        } catch (DukeException e) {
            assertEquals("Please specify the deadline description", e.getMessage());
        }
    }

    @Test
    public void execute_deadlineMissingDeadline_exceptionThrown() {
        ChatBot bot = new ChatBot();
        Parser p = new Parser();
        try {
            p.parse("deadline homework", bot);
        } catch (DukeException e) {
            assertEquals("Please specify the deadline time", e.getMessage());
        }
    }

    @Test
    public void execute_deadlineInvalidDeadlineFormat_exceptionThrown() {
        ChatBot bot = new ChatBot();
        Parser p = new Parser();
        try {
            p.parse("deadline homework /by 08/22/2021 2100", bot);
        } catch (DukeException e) {
            assertEquals("The format of date & time is wrong. Please use {dd/mm/yyyy hhmm}", e.getMessage());
        }
    }
}
