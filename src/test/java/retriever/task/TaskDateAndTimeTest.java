package retriever.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskDateAndTimeTest {
    @Test
    public void toString_aDateInStringFormat_success() {
        assertEquals("Aug 21 2021", new TaskDateAndTime("21/08/2021").toString());
    }

    @Test
    public void isValidTestMethod_aDateInputInStringFormat_success() {
        assertEquals(true, new TaskDateAndTime("23/08/2021").isValidDate());
    }

    @Test void isValidTestMethod_aDateWronglyFormatted_success() {
        assertEquals(false, new TaskDateAndTime("21 Jan 2001").isValidDate());
    }
}
