package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class DeadlineTest {
    @Test
    public void testToString1() throws DukeException {
        Deadline deadline = new Deadline("finish ip", "2021-03-21");
        assertEquals("[D][ ] finish ip (by: Mar 21 2021)", deadline.toString());
    }

    @Test
    public void testToString2() throws DukeException {
        Deadline deadline = new Deadline("return library book", "2021-10-27");
        assertEquals("[D][ ] return library book (by: Oct 27 2021)", deadline.toString());
    }

    @Test
    public void testInvalidInput1() {
        assertThrows(DukeException.class, () -> new Deadline("return library book", "Sunday"));
    }

    @Test
    public void testInvalidInput2() {
        assertThrows(DukeException.class, () -> new Deadline("return library book", "2021-93-10"));
    }

    @Test
    public void testToData1() throws DukeException {
        Deadline deadline = new Deadline("finish ip", "2021-03-21");
        assertEquals("D~S~0~S~finish ip~S~2021-03-21", deadline.toData());
    }

    @Test
    public void testToData2() throws DukeException {
        Deadline deadline = new Deadline("return library book", "2021-10-27");
        assertEquals("D~S~0~S~return library book~S~2021-10-27", deadline.toData());
    }
}
