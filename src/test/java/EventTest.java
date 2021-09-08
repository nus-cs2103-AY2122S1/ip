package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void testEvent(){
        Event test = new Event("testEvent ","Tues 2-4pm", "EVENT");
        assertEquals("[E][ ] testEvent (at: Tues 2-4pm)", test.toString());
    }
}

