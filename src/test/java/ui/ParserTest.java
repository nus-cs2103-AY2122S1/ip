package ui;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import task.Task;


public class ParserTest {
    @Test
    public void testCreateTodoTask() {
        String expected = "[T][X] test";
        String command = "todo";
        String input = "test";
        Task todoTask = Parser.parseStringIntoTask(input, command, true);
        assertEquals(todoTask.toString(), expected);
    }

    @Test
    public void testUnformattedDeadline() {
        String expected = "[D][ ] test (by: Thursday)";
        String command = "deadline";
        String input = "test /by Thursday";
        Task deadlineTask = Parser.parseStringIntoTask(input, command, false);
        assertEquals(deadlineTask.toString(), expected);
    }

    @Test
    public void testFormattedEvent() {
        String expected = "[E][X] test (at: Aug 25 2021)";
        String command = "event";
        String input = "test /at 2021-08-25";
        Task eventTask = Parser.parseStringIntoTask(input, command, true);

        assertEquals(eventTask.toString(), expected);
    }
}
