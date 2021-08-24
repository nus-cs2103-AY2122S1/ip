import duke.Event;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.assertEquals;

class EventTest {

    String date = "2000-10-10";
    Event ev = new Event("fun run", LocalDate.parse(date), "1000");

    @Test
    void testToString() {
        assertEquals("[E][ ] fun run (at: 10 Oct 2000 1000)", ev.toString());
    }

    @Test
    void getAt() {
        assertEquals("2000-10-10 1000", ev.getAt());
    }
}