package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class DateTimeTest {

    @Test
    void getFormattedDate() {
        DateTime dateTime = new DateTime("2021-10-20", "23:59");
        assertEquals("20 Oct 2021", dateTime.getFormattedDate());
    }

    @Test
    void getFormattedTime() {
        DateTime dateTime = new DateTime("2021-10-20", "01:59");
        assertEquals("01.59AM", dateTime.getFormattedTime());
    }
}
