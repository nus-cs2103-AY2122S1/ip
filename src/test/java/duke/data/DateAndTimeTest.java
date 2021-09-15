package duke.data;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.exceptions.InvalidDateAndTimeException;
import duke.ui.Message;

public class DateAndTimeTest {
    @Test
    public void reformattedDateAndTime_commandInput_success1() throws InvalidDateAndTimeException {
        DateAndTime dateAndTime = new DateAndTime("11/12/2021 11:00");
        String expectedOutput = "Dec 11 2021, 11:00";
        assertEquals(expectedOutput, dateAndTime.getReformattedDateAndTime());
    }

    @Test
    public void reformattedDateAndTime_commandInput_success2() throws InvalidDateAndTimeException {
        DateAndTime dateAndTime = new DateAndTime("15/09/2021");
        String expectedOutput = "Sep 15 2021";
        assertEquals(expectedOutput, dateAndTime.getReformattedDateAndTime());
    }

    @Test
    public void reformattedDateAndTime_commandInput_error1() throws InvalidDateAndTimeException {
        DateAndTime dateAndTime = new DateAndTime("9/12/2021");
        String expectedOutput = Message.MESSAGE_INVALID_DATE;
        assertEquals(expectedOutput, dateAndTime.getReformattedDateAndTime());
    }
}
