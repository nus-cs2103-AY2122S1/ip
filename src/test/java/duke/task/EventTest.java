package duke.task;

import duke.date.Date;
import duke.exception.BadInputFormatException;
import duke.exception.InvalidDateException;
import org.json.simple.JSONObject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class EventTest {
    @Test
    public void testStringConversion() throws InvalidDateException {
        assertEquals(
                "[E][ ] event 1 (at: Oct 10 2020)",
                new Event("event 1", Date.of("2020-10-10")).toString());
    }

    @Test
    public void testInvalidDate() {
        assertThrows(InvalidDateException.class, () -> new Event("event 1", Date.of("10-10-2020")));
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testJSONConversion() throws BadInputFormatException, InvalidDateException {
        JSONObject object = new JSONObject();
        object.put("type", "event");
        object.put("isDone", false);
        object.put("description", "event 1");
        object.put("date", "2020-10-10");
        assertEquals(object.toJSONString(), new Event("event 1 /at 2020-10-10").toJsonObject().toJSONString());
    }

    @Test
    public void testInvalidInputFormat() {
        assertThrows(BadInputFormatException.class, () -> new Event("event 1"));
    }

    @Test
    public void testFactoryMethod1() throws BadInputFormatException, InvalidDateException {
        assertEquals(
                new Event("event 1 /at 2020-10-10").toString(),
                Event.of("event 1 /at 2020-10-10").toString());
    }

    @Test
    public void testFactoryMethod2() throws InvalidDateException, BadInputFormatException {
        assertEquals(
                new Event("event 1 /at 2020-10-10").toString(),
                Event.of("event 1", Date.of("2020-10-10"), false).toString());
    }
}
