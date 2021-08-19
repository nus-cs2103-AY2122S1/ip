package util;

import exception.DukeExtractCommandException;
import exception.DukeTaskNumberOutOfBoundsException;
import exception.DukeUnknownException;
import org.junit.jupiter.api.Test;
import task.EventDateTime;
import task.Operation;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class CommandUtilsTest {

    @Test
    void extractOperation() throws DukeExtractCommandException, DukeUnknownException {
        String command = "event project meeting /at 2021-08-06 14:00 16:00";
        assertEquals(Operation.EVENT, CommandUtils.extractOperation(command));
    }

    @Test
    void extractTaskNumber() throws DukeExtractCommandException, DukeTaskNumberOutOfBoundsException {
        String command = "done 1";
        assertEquals(1, CommandUtils.extractTaskNumber(command));
    }

    @Test
    void extractTaskDescription() throws DukeExtractCommandException {
        String command = "event project meeting /at 2021-08-06 14:00 16:00";
        String description = "project meeting /at 2021-08-06 14:00 16:00";
        assertEquals(description, CommandUtils.extractTaskDescription(command));
    }

    @Test
    void extractTaskDetails() throws DukeExtractCommandException {
        String description = "project meeting /at 2021-08-06 14:00 16:00";
        String[] details = new String[]{"project meeting", "2021-08-06 14:00 16:00"};
        assertArrayEquals(details, CommandUtils.extractTaskDetails(description, " /at "));
    }

    @Test
    void extractEventDatetime() throws DukeExtractCommandException {
        String dateTime = "2021-08-06 14:00 16:00";
        LocalDate atDate = DateTimeUtils.parseDate("2021-08-06");
        LocalTime startTime = DateTimeUtils.parseTime("14:00");
        LocalTime endTime = DateTimeUtils.parseTime("16:00");
        EventDateTime eventDateTime = CommandUtils.extractEventDatetime(dateTime, " ");
        EventDateTime expected = new EventDateTime(atDate, startTime, endTime);
        assertEquals(expected.getAtDate(), eventDateTime.getAtDate());
        assertEquals(expected.getStartTime(), eventDateTime.getStartTime());
        assertEquals(expected.getEndTime(), eventDateTime.getEndTime());
    }

    @Test
    void extractDeadlineDateTime() throws DukeExtractCommandException {
        String dateTime = "2021-06-06 18:00";
        LocalDateTime byDateTime = DateTimeUtils.parseDateTime("2021-06-06 18:00");
        assertEquals(byDateTime, CommandUtils.extractDeadlineDateTime(dateTime));
    }
}
