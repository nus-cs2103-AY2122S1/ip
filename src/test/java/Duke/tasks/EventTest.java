package duke.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class EventTest {
    @Test
    public void fromStringToDateTime_correct_success() {
        Event actual = new Event("jogging", " 2020/09/13 08:22");
        LocalDateTime actualFormatted = actual.fromStringToDateTime(actual.time);
        LocalDateTime expectedFormatted = LocalDateTime.parse("2020/09/13 08:22", DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm"));
        assertEquals(actualFormatted, expectedFormatted);
    }

    @Test
    public void toString_correct_success() {
        Event actual = new Event("jogging", " 2020/09/13 08:22");
        String actualString = actual.toString();
        String expectedString = "[E] [ ] jogging:2020-09-13T08:22";
        assertEquals(actualString, expectedString);
    }
}