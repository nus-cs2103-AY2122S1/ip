package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.DukeException;

public class EventTest {
    @Test
    public void toStringTest() {
        String input = "mumu /at 2021-09-10 2359";
        String expected = "[E][ ] mumu (10 Sep 2021 11.59PM)";
        try {
            Event event = new Event(input);
            assertEquals(expected, event.toString());
        } catch (DukeException e) {
            //This shouldn't happen
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void getSaveTest() {
        String input = "mumu /at 2021-09-10 2359";
        String expected = "E1|mumu|2021-09-10 2359";
        try {
            Event event = new Event(input);
            event.setDone();
            assertEquals(expected, event.getSave());
        } catch (DukeException e) {
            //This shouldn't happen
            System.out.println(e.getMessage());
        }
    }
}
