package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.items.Event;

public class EventTest {
    @Test
    void EventPrintTest() {
        try {
            Event t1 = new Event("thing", "1998-03-10");
            t1.addTag("mytag");
            assertEquals(t1.toString(), "[E][] thing (tag: mytag) (at: Mar 10 1998)");
        } catch (DukeException e) {
            e.printStackTrace();
        }
    }

    @Test
    void EventDonePrintTest() {
        try {
            Event t2 = new Event("thing", "1998-03-10");
            t2.markAsDone();
            assertEquals(t2.toString(), "[E][X] thing (at: Mar 10 1998)");
        } catch (DukeException e) {
            e.printStackTrace();
        }
    }
}