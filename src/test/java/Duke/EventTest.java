package Duke;

import Duke.Tasks.Event;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    public static DateTimeFormatter validFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    @Test
    public void markDoneTest() {
        Event e = new Event("study", LocalDateTime.parse("2021-08-22 17:19", validFormat), false);
        assertEquals(e.markDone().toString(), "[E][X] study (at: Aug 22 2021 17:19)");
    }

    @Test
    public void fileFormatetedStringTest() {
        Event e = new Event("study", LocalDateTime.parse("2021-08-22 17:19", validFormat), false);
        assertEquals(e.formatChange(), "E | 0 | study | 2021-08-22 17:19");
    }
}