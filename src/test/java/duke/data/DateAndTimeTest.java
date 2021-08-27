package duke.data;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.data.exceptions.InvalidDateAndTimeException;

public class DateAndTimeTest {
    @Test
    public void reformattedDateAndTime_commandInput_success() throws InvalidDateAndTimeException {
        DateAndTime dateAndTime = new DateAndTime("event meeting /at 11/12/2021 11:00");
        String expectedOutput = "Dec 11 2021, 11:00";
        assertEquals(expectedOutput, dateAndTime.getReformattedDateAndTime());
    }
}
