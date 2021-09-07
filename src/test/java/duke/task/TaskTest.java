package duke.task;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class TaskTest {
    @Test
    public void constructor_success() {
        Task task1 = new Event("test event", "7/9/2021");
        Task task2 = new Todo("test todo");
        String expected = "Sep 7 2021";
        assertEquals(expected, task1.atOrBy);
        assertNull(task2.atOrBy);
    }

    @Test
    public void isWithinMonthOrDay_success() {
        Task task1 = new Event("test event", "7/9/2021");
        Task task2 = new Todo("test todo");
        int expected = 1;
        LocalDate dateNow = LocalDate.now();
        String now = dateNow.format(DateTimeFormatter.ofPattern("MMM d yyyy", Locale.ENGLISH));
        assertEquals("Sep 7 2021", now);
        assertEquals(expected, task1.isWithinMonthOrDay(now));
        assertNull(task2.atOrBy);
    }
}
