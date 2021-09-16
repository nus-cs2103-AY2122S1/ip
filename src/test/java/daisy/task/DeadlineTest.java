package daisy.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class DeadlineTest {

    @Test
    public void testStringConversion() {
        Deadline deadline = new Deadline("project", LocalDate.of(2021, 9, 1));
        assertEquals("[D][ ] project (by: Sep 1 2021)", deadline.toString());
    }

    @Test
    public void stringConversion_doneTask_indicated() {
        Deadline deadline = new Deadline("project", LocalDate.of(2021, 9, 1));
        deadline.markAsDone();
        assertEquals("[D][X] project (by: Sep 1 2021)", deadline.toString());
    }

    @Test
    public void testFileFormatConversion() {
        Deadline deadline = new Deadline("project", LocalDate.of(2021, 9, 1));
        assertEquals("D // 0 // project // 2021-09-01 // ", deadline.convertToFileFormat());
    }

    @Test
    public void fileFormatConversion_doneTask_indicated() {
        Deadline deadline = new Deadline("project", LocalDate.of(2021, 9, 1));
        deadline.markAsDone();
        assertEquals("D // 1 // project // 2021-09-01 // ", deadline.convertToFileFormat());
    }
}
