package nyx;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void testStringConversion_done(){
        Deadline deadline = new Deadline("return book", "2021-04-12 15:35", true);
        assertEquals("[D][X] return book (by: Mon 12 Apr 2021, 3:35PM)", deadline.toString());
    }

    @Test
    public void testStringConversion_notDone() {
        Deadline deadline = new Deadline("return book", "2021-04-12 15:35");
        assertEquals("[D][ ] return book (by: Mon 12 Apr 2021, 3:35PM)", deadline.toString());
    }

    @Test
    public void testDataFormatConversion_notDone() {
        Deadline deadline = new Deadline("return book", "2021-04-12 15:35");
        assertEquals("D, 0, return book, 2021-04-12 15:35\n", deadline.dataFormat());
    }

    @Test
    public void testDataFormatConversion_done() {
        Deadline deadline = new Deadline("return book", "2021-04-12 15:35", true);
        assertEquals("D, 1, return book, 2021-04-12 15:35\n", deadline.dataFormat());
    }

    @Test
    public void testSetDone() {
        Deadline deadline = new Deadline("return book", "2021-04-12 15:35");
        deadline.setDone();
        assertEquals("[D][X] return book (by: Mon 12 Apr 2021, 3:35PM)", deadline.toString());
    }
}
