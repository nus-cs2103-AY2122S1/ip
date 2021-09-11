package command;

import duke.command.InputType;
import duke.command.Parser;
import duke.exception.InvalidTaskException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {
    @Test
    public void judgeTypeTest() {
        String invalidInput = "This is not a valid input";
        String validDeadline = "deadline go to school /by 2021-08-10 00:00";
        String invalidDeadline = "deadline go to school 2021-09-10 00:00";
        String validTodo = "todo Finish ip";
        String invalidTodo = "Finish ip todo";
        String validEvent = "event go to school /at 2021-08-10 00:00 01:00";
        String invalidEvent = "event go to school 2021-08-10 00:00 01:00";

        assertEquals(InputType.UNKNOWN, Parser.judgeType(invalidInput));
        assertEquals(InputType.DEADLINE, Parser.judgeType(validDeadline));
        assertEquals(InputType.UNKNOWN, Parser.judgeType(invalidDeadline));
        assertEquals(InputType.TODO, Parser.judgeType(validTodo));
        assertEquals(InputType.UNKNOWN, Parser.judgeType(invalidTodo));
        assertEquals(InputType.EVENT, Parser.judgeType(validEvent));
        assertEquals(InputType.UNKNOWN, Parser.judgeType(invalidEvent));
        assertEquals(InputType.DONE, Parser.judgeType("done 1"));
        assertEquals(InputType.LIST, Parser.judgeType("list"));
        assertEquals(InputType.DELETE, Parser.judgeType("delete 1"));
        assertEquals(InputType.HELP, Parser.judgeType("help"));
    }

    @Test
    public void testTodoValidityTest() throws InvalidTaskException {
        String input1 = "todo task1";
        String input2 = "todo task2";
        String input3 = "todo task3";
        Todo expectedTask1 = new Todo("task1");
        Todo expectedTask2 = new Todo("task2");
        Todo expectedTask3 = new Todo("Task3");
        assertEquals(expectedTask1, Parser.testTodoValidity(input1));
        assertEquals(expectedTask2, Parser.testTodoValidity(input2));
        assertNotEquals(expectedTask3, Parser.testTodoValidity(input3));
    }

    @Test
    public void testDeadlineValidityTest() throws InvalidTaskException {
        String input1 = "deadline task1 /by 2020-12-08 09:00";
        String input2 = "deadline task2 /by 2010-12-08 20:00";
        String input3 = "deadline task2 2010-12-08 20:00";
        Deadline expectedTask1 = new Deadline("task1", "2020-12-08 09:00");
        Deadline expectedTask2 = new Deadline("task2", "2010-12-08 20:00");
        assertEquals(expectedTask1, Parser.testDeadlineValidity(input1));
        assertEquals(expectedTask2, Parser.testDeadlineValidity(input2));
        assertNotEquals(expectedTask2, Parser.testDeadlineValidity(input3));
    }

    @Test
    public void testEventValidityTest() throws InvalidTaskException {
        String input1 = "event event1 /at 2020-12-08 09:00 10:00";
        String input2 = "event event2 /at 2012-09-23 03:01 12:00";
        Event expectedEvent1 = new Event("event1", "2020-12-08 09:00 10:00");
        Event expectedEvent2 = new Event("event2", "2012-09-23 03:01 12:00");
        assertEquals(expectedEvent1, Parser.testEventValidity(input1));
        assertEquals(expectedEvent2, Parser.testEventValidity(input2));
    }

    @Test
    public void isByeTest() {
        assertTrue(Parser.isBye("bye"));
        assertFalse(Parser.isBye("BYE"));
    }
}
