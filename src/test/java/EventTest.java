import org.junit.jupiter.api.Test;
import duke.information.Event;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {

    @Test
    public void testInput(){
        Event test = new Event("something", "somewhere");
        assertEquals("[E][ ] something (at: somewhere)", test.toString());
    }
    @Test
    public void testMarkAsDone(){
        Event test = new Event("something", "somewhere");
        test.markAsDone();
        assertEquals("[E][X] something (at: somewhere)", test.toString());
    }

    @Test
    public void testToData() {
        Event test = new Event("something", "somewhere");
        test.markAsDone();
        assertEquals("E|1|something|somewhere\n", test.toData());
    }

}