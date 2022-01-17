import duke.Event;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class EventTest {
    @Test
    public void testStringOutput(){
        assertEquals("[E][ ] Finish iP Tasks (at: Mar 12 2002 11:59pm)",
                new Event("Finish iP Tasks", "12-03-2002 2359").toString());
    }

}
