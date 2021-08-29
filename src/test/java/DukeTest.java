import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.time.Month;

import org.junit.jupiter.api.Test;

import duke.tasklist.Deadline;
import duke.tasklist.Event;
import duke.tasklist.Todo;


/**
 * Represents JUnit Tester for Duke application.
 *
 */
public class DukeTest {
    /**
     * Tests creating and modifying Deadline object.
     */
    @Test
    public void test_create_deadline() {
        Deadline dl = new Deadline("return book ", LocalDateTime.of(2021,
                                        Month.JULY, 29, 12, 0, 0));

        assertEquals(dl.toString(), "[D][ ] return book (by: 2021 Jul 29 12.00 PM)");
        dl.setDone();
        assertEquals(dl.toString(), "[D][X] return book (by: 2021 Jul 29 12.00 PM)");
        assertEquals(dl.save(), "D | 1 | return book | 2021 Jul 29 12.00 PM");
    }

    /**
     * Tests creating and modifying Event object.
     */
    @Test
    public void test_create_event() {
        Event ev = new Event("project meeting ", LocalDateTime.of(2021,
                Month.AUGUST, 2, 14, 0, 0));

        assertEquals(ev.toString(), "[E][ ] project meeting (at: 2021 Aug 2 2.00 PM)");
        ev.setDone();
        assertEquals(ev.toString(), "[E][X] project meeting (at: 2021 Aug 2 2.00 PM)");
        assertEquals(ev.save(), "E | 1 | project meeting | 2021 Aug 2 2.00 PM");
    }

    /**
     * Tests creating and modifying Todo object.
     */
    @Test
    public void test_create_todo() {
        Todo td = new Todo("read book");

        assertEquals(td.toString(), "[T][ ] read book");
        td.setDone();
        assertEquals(td.toString(), "[T][X] read book");
        assertEquals(td.save(), "T | 1 | read book");
    }
}
