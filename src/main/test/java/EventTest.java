import duke.Event;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class EventTest {
    @Test
    public void testStringOutput(){
        assertEquals("[E][ ] Finish iP Tasks (at: 7pm)",
                new Event("Finish iP Tasks", "7pm").toString());
    }

}
