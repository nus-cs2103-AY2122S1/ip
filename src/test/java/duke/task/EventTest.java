package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.json.simple.JSONObject;
import org.junit.jupiter.api.Test;

import duke.date.Date;
import duke.exception.BadInputFormatException;
import duke.exception.InvalidDateException;

public class EventTest {
    @Test
    public void testStringConversion() throws InvalidDateException {
        assertEquals(
                "[E][ ] event 1 (at: Oct 10 2020 to Oct 11 2020)",
                new Event("event 1", Date.of("2020-10-10"), Date.of("2020-10-11")).toString());
    }

    @Test
    public void testInvalidDate() {
        assertThrows(InvalidDateException.class, () -> new Event("event 1",
                        Date.of("10-10-2020"),
                        Date.of("11-10-2020")));
    }

    @Test
    @SuppressWarnings("unchecked") // Type warning due to JSON simple library. Type safety guaranteed. Just use it.
    public void testJsonConversion() throws BadInputFormatException, InvalidDateException {
        JSONObject object = new JSONObject();
        object.put("type", "event");
        object.put("isDone", false);
        object.put("description", "event 1");
        object.put("from", "2020-10-10");
        object.put("until", "2020-10-11");
        assertEquals(object.toJSONString(),
                new Event("event 1 /at 2020-10-10 /to 2020-10-11").toJsonObject().toJSONString());
    }

    @Test
    public void testInvalidInputFormat() {
        assertThrows(BadInputFormatException.class, () -> new Event("event 1"));
    }

    @Test
    public void testFactoryMethod1() throws BadInputFormatException, InvalidDateException {
        assertEquals(
                new Event("event 1 /at 2020-10-10 /to 2020-10-11").toString(),
                Event.of("event 1 /at 2020-10-10 /to 2020-10-11").toString());
    }

    @Test
    public void testFactoryMethod2() throws InvalidDateException, BadInputFormatException {
        assertEquals(
                new Event("event 1 /at 2020-10-10 /to 2020-10-11").toString(),
                Event.of("event 1", Date.of("2020-10-10"), Date.of("2020-10-11"), false).toString());
    }
}
