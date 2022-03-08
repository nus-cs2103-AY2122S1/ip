package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;


public class DeadlineTest {
    @Test
    void testFormatToSave() {
        Deadline dl = new Deadline("hi yay", "26 August 2021");
        assertEquals("D | 0 | hi yay | 26 August 2021", dl.formatToSave());
    }

    @Test
    void testToStringDate() {
        Deadline dl = new Deadline("hi yay", "26 August 2021");
        assertEquals("[D][ ] hi yay\n    (by: 26 August 2021)", dl.toString());
    }

    @Test
    void testToStringNormal() {
        Deadline dl = new Deadline("sleep", "12am midnight");
        assertEquals("[D][ ] sleep\n    (by: 12am midnight)", dl.toString());
    }

    @Test
    void testMarkAsDoneToString() {
        Deadline dl = new Deadline("hi yay", "26 August 2021");
        dl.markAsDone();
        assertEquals("[D][✓] hi yay\n    (by: 26 August 2021)", dl.toString());
    }

    @Test
    void testMarkAsDoneFormatToSave() {
        Deadline dl = new Deadline("sleep", "12am midnight");
        dl.markAsDone();
        assertEquals("D | 1 | sleep | 12am midnight", dl.formatToSave());
    }
}
