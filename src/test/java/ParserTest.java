import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import back.Deadline;
import back.Event;
import back.Parser;
import back.Task;
import back.Todo;

/**
 * This class represents a test file for Duke.java.
 */

public class ParserTest {
    Todo TODO_INSTANCE = new Todo("sample todo");
    Event EVENT_INSTANCE = new Event("cake", LocalDate.parse("2020-11-18"));
    Deadline DEADLINE_INSTANCE = new Deadline("2100 Lab3", LocalDate.parse("2020-09-16"));


    @Test
    public void taskToString_todoInstance_stringReturned() {
        String output = Parser.taskToString(TODO_INSTANCE);
        assertEquals("T\r\nsample todo\r\n0\r\n", output);
    }

    @Test
    public void taskToString_eventInstance_stringReturned() {
        String output = Parser.taskToString(EVENT_INSTANCE);
        assertEquals("E\r\ncake\r\n2020-11-18\r\n0\r\n", output);
    }

    @Test
    public void taskToString_deadlineInstance_stringReturned() {
        String output = Parser.taskToString(DEADLINE_INSTANCE);
        assertEquals("D\r\n2100 Lab3\r\n2020-09-16\r\n0\r\n", output);
    }

    @Test
    public void listToString_listOfTasks_stringReturned() {
        ArrayList<Task> list = new ArrayList<>();
        list.add(TODO_INSTANCE);
        list.add(DEADLINE_INSTANCE);
        String output = Parser.listToString(list);
        assertEquals("T\r\nsample todo\r\n0\r\nD\r\n2100 Lab3\r\n2020-09-16\r\n0\r\n", output);
    }

    @Test
    public void parseDateString_fullDateString_localDateReturned() {
        LocalDate output = Parser.parseDateString("2020-11-11");
        assertEquals(LocalDate.of(2020, 11, 11), output);

    }
}
