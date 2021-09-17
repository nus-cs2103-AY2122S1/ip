import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import hyddd.exceptions.HydddException;
import hyddd.logics.Parser;
/**
 * @author Hang Zelin
 *
 * A JUnit class that tests some methods in Parser.
 */
public class ParserTest {

    @Test
    public void testParserTime() {
        assertEquals(LocalDate.of(2019, 12, 2).atTime(18, 0), new Parser("").parseTime("2/12/2019 1800"));
        assertEquals(LocalDate.of(2020, 9, 23).atTime(15, 25), new Parser("").parseTime("23/9/2020 1525"));
        assertEquals(LocalDate.of(2019, 10, 15).atTime(0, 0), new Parser("").parseTime("2019-10-15"));
        assertNull(new Parser("").parseTime("ukpkmkk"));
    }

    @Test
    public void testGetSaveTask() {
        assertEquals("borrow book", new Parser("T | 0 | borrow book").getSaveTask());
        assertEquals("return book", new Parser("D | 0 | return book | 2/12/2019 1800").getSaveTask());
        assertEquals("project meeting", new Parser("E | 0 | project meeting | I don't know the time").getSaveTask());
        assertEquals("project splashdown", new Parser("E | 0 | project splashdown | 15/10/2019 0000").getSaveTask());
    }

    @Test
    public void testGetSaveTime() {
        assertEquals("", new Parser("T | 0 | borrow book").getSaveTime());
        assertEquals("2/12/2019 1800", new Parser("D | 0 | return book | 2/12/2019 1800").getSaveTime());
        assertEquals("", new Parser("E | 0 | project meeting | I don't know the time").getSaveTime());
        assertEquals("15/10/2019 0000", new Parser("E | 0 | project splashdown | 15/10/2019 0000").getSaveTime());
    }

    @Test
    public void testGetOperationType() {
        try {
            assertEquals("todo", new Parser("todo borrow book").getOperationType());
            assertEquals("deadline", new Parser("deadline return book /by 2/12/2019 1800").getOperationType());
            assertEquals("done", new Parser("done 3").getOperationType());
            assertEquals("tell", new Parser("tell 60").getOperationType());
            assertEquals("bye", new Parser("bye").getOperationType());
            assertEquals("delete", new Parser("delete 10").getOperationType());
            assertEquals("list", new Parser("list").getOperationType());
        } catch (HydddException e) {
            fail(); //should not reach this.
        }

    }

    @Test
    public void testGetTask() {
        try {
            assertEquals("borrow book", new Parser("todo borrow book").getTask());
            assertEquals("return book", new Parser("deadline return book /by 2/12/2019 1800").getTask());
            assertEquals("project meeting", new Parser("event project meeting /at Aug 6th 6pm").getTask());
            assertEquals("project splashdown", new Parser("event project splashdown /at 2019-10-15").getTask());
        } catch (HydddException e) {
            fail(); //should not reach this.
        }
    }

    @Test
    public void testGetTime() {
        try {
            assertEquals("", new Parser("todo borrow book").getTime());
            assertEquals("2/12/2019 1800", new Parser("deadline return book /by 2/12/2019 1800").getTime());
            assertEquals("Aug 6th 6pm", new Parser("event project meeting /at Aug 6th 6pm").getTime());
            assertEquals("2019-10-15", new Parser("event project splashdown /at 2019-10-15").getTime());
        } catch (HydddException e) {
            fail(); //should not reach this.
        }
    }

    @Test
    public void testGetIndex() {
        assertEquals(19, new Parser("delete 20").getIndex());
        assertEquals(14, new Parser("done 15").getIndex());
        assertEquals(20312130, new Parser("done 20312131").getIndex());
    }
}
