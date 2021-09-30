package retriever.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TaskDateAndTimeTest {
    @Test
    public void toString_aDateInStringFormat_success() {
        assertEquals("Aug 21 2021", new TaskDateAndTime("21/08/2021").toString());
    }

    @Test
    public void toString_aDateInputInStringFormat_success() {
        assertEquals("Aug 23 2021", new TaskDateAndTime("23/08/2021").toString());
    }

    @Test
    public void toString_aDateWronglyFormatted_success() {
        assertEquals("Jan 21 2001", new TaskDateAndTime("21/01/2001").toString());
    }
}
