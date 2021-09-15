package duke.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import duke.exception.DukeException;
import duke.exception.NoDescriptionAndTimeException;
import duke.exception.NoDescriptionException;
import duke.exception.NoTimeException;

import duke.task.Task;
import org.junit.jupiter.api.Test;

public class ParserTest {
    @Test
    public void parseDescriptionAndTime_success() throws DukeException {
        String[] inputs = {"deadline test /by 22 Jun 2021", "deadline       test /by 22 Jun 2021",
                "deadline test /by           22 Jun 2021", "deadline   test    /by       22 Jun 2021"};

        for (int i = 0; i < inputs.length; i++) {
            String[] userInput = inputs[i].split(" ", 2);
            String[] parseInput = Parser.parseDescriptionAndTime(userInput, "/by", "deadline");
            assertEquals("test", parseInput[0].trim());
            assertEquals("22 Jun 2021", parseInput[1].trim());
        }

        String[] inputs2 = {"event test /at 22 Jun 2021", "event       test /at 22 Jun 2021",
                "event test /at           22 Jun 2021", "event   test    /at       22 Jun 2021"};

        for (int i = 0; i < inputs2.length; i++) {
            String[] userInput = inputs2[i].split(" ", 2);
            String[] parseInput = Parser.parseDescriptionAndTime(userInput, "/at", "event");
            assertEquals("test", parseInput[0].trim());
            assertEquals("22 Jun 2021", parseInput[1].trim());
        }
    }

    @Test
    public void parseDescriptionAndTime_noTime_exceptionThrown() {
        Throwable exception;

        String input = "deadline test";
        String[] userInput = input.split(" ", 2);
        exception = assertThrows(NoTimeException.class,
                () -> Parser.parseDescriptionAndTime(userInput, "/by", "deadline"));
        assertEquals("OOPS! The time of deadline cannot be empty.", exception.getMessage());

        input = "deadline test /by";
        String[] userInput1 = input.split(" ", 2);
        exception = assertThrows(NoTimeException.class,
                () -> Parser.parseDescriptionAndTime(userInput1, "/by", "deadline"));
        assertEquals("OOPS! The time of deadline cannot be empty.", exception.getMessage());

        input = "event test";
        String[] userInput2 = input.split(" ", 2);
        exception = assertThrows(NoTimeException.class,
                () -> Parser.parseDescriptionAndTime(userInput2, "/at", "event"));
        assertEquals("OOPS! The time of event cannot be empty.", exception.getMessage());

        input = "event test /at";
        String[] userInput3 = input.split(" ", 2);
        exception = assertThrows(NoTimeException.class,
                () -> Parser.parseDescriptionAndTime(userInput3, "/at", "event"));
        assertEquals("OOPS! The time of event cannot be empty.", exception.getMessage());
    }

    @Test
    public void parseDescriptionAndTime_noDescription_exceptionThrown() {
        Throwable exception;

        String input = "deadline /by 20 Nov 2021";
        String[] userInput = input.split(" ", 2);
        exception = assertThrows(NoDescriptionException.class,
                () -> Parser.parseDescriptionAndTime(userInput, "/by", "deadline"));
        assertEquals("OOPS! The description of deadline cannot be empty.", exception.getMessage());

        input = "event /at 20 Nov 2021";
        String[] userInput1 = input.split(" ", 2);
        exception = assertThrows(NoDescriptionException.class,
                () -> Parser.parseDescriptionAndTime(userInput1, "/at", "event"));
        assertEquals("OOPS! The description of event cannot be empty.", exception.getMessage());
    }

}