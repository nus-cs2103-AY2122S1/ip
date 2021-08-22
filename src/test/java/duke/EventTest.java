package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class EventTest {
    @Test
    public void testEventToString(){
        assertEquals("[E][ ] placeholder (at: Jun 6 2022)",
                new Event("placeholder", "2022-06-06").toString());
    }

    @Test
    public void testEventMarkAsDone(){
        Task temp = new Event("placeholder", "2022-06-06");
        temp.markAsDone();
        assertEquals("[E][X] placeholder (at: Jun 6 2022)", temp.toString());
    }
}
