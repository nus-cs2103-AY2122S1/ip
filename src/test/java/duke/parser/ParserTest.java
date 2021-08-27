package duke.parser;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class ParserTest {
    @Test
    public void findDateRegex_date_success() {
        String date = "fluff fluff 2021-08-23 fluff";
        LocalDate localDate = Parser.findDate(date);
        LocalDate expected = LocalDate.parse("2021-08-23");
        assertEquals(expected, localDate);
    }

    @Test
    public void findDateRegex_date_fail() {
        String date = "fluff fluff no dates here fluff";
        LocalDate localDate = Parser.findDate(date);
        assertNull(localDate);
    }

    @Test
    public void findTimeRegex_time_success() {
        String time = "fluff fluff 1900";
        String actual = Parser.findTime(time);
        assertEquals("1900", actual);
    }

    @Test
    public void findTimeRegex_time_fail() {
        String time = "fluff fluff no time here";
        String actual = Parser.findTime(time);
        assertNull(actual);
    }

    @Test
    public void convertTimeAm_time24h_success() {
        String time = "1000";
        String actual = Parser.convertTime(time);
        assertEquals("10.00 AM", actual);
    }

    @Test
    public void convertTimePm_time24h_success() {
        String time = "2130";
        String actual = Parser.convertTime(time);
        assertEquals("9.30 PM", actual);
    }

    @Test
    public void parseLineTodo_todo_success() {
        String line = "[T][ ] test sentence";
        Todo actual = (Todo) Parser.parseFromFile(line);
        assertEquals(line, actual.toString());
    }

    @Test
    public void parseLineDeadline_deadline_success() {
        String line = "[D][ ] test sentence(23 AUGUST 2021 10.30 PM)";
        Deadline actual = (Deadline) Parser.parseFromFile(line);
        assertEquals(line, actual.toString());
    }

    @Test
    public void parseLineEvent_event_success() {
        String line = "[E][ ] test sentence(23 AUGUST 2021 10.30 PM)";
        Event actual = (Event) Parser.parseFromFile(line);
        assertEquals(line, actual.toString());
    }
}
