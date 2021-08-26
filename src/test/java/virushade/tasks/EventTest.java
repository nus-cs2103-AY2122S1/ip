package virushade.tasks;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void toStringTest() {
        assertEquals("[E][x] EventTask /at 12:05AM",
                new Event("EventTask", "0005", true).toString());
    }
}
