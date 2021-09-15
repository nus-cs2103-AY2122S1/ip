package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import org.junit.jupiter.api.Test;

public class TaskTest {
    @Test
    public void constructor_success() {
        Task task1 = new Event("test event", "Sep 7 2021");
        Task task2 = new Todo("test todo");
        String expected = "Sep 7 2021";
        assertEquals(expected, task1.atOrBy);
        assertNull(task2.atOrBy);
    }

    @Test
    public void withinMonthOrDay_day_success() {
        LocalDate dateNow = LocalDate.now();
        String now = dateNow.format(DateTimeFormatter.ofPattern("MMM d yyyy", Locale.ENGLISH));
        Task task1 = new Event("test event", now);
        Task task2 = new Todo("test todo");
        String expected = "week";
        assertEquals(expected, task1.withinMonthOrWeek(now));
        assertNull(task2.atOrBy);
    }
}
