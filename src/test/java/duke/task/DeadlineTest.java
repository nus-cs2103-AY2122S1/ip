package duke.task;

import duke.date.Date;
import duke.exception.BadInputFormatException;
import duke.exception.InvalidDateException;
import org.json.simple.JSONObject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DeadlineTest {
    @Test
    public void testStringConversion() throws InvalidDateException {
        assertEquals(
                "[D][ ] deadline 1 (by: Oct 10 2020)",
                new Deadline("deadline 1", Date.of("2020-10-10")).toString());
    }

    @Test
    public void testInvalidDate() {
        assertThrows(InvalidDateException.class, () -> new Deadline("deadline 1", Date.of("10-10-2020")));
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testJSONConversion() throws BadInputFormatException, InvalidDateException {
        JSONObject object = new JSONObject();
        object.put("type", "deadline");
        object.put("isDone", false);
        object.put("description", "deadline 1");
        object.put("date", "2020-10-10");
        assertEquals(
                object.toJSONString(),
                new Deadline("deadline 1 /by 2020-10-10").toJsonObject().toJSONString());
    }

    @Test
    public void testInvalidInputFormat() {
        assertThrows(BadInputFormatException.class, () -> new Deadline("deadline 1"));
    }

    @Test
    public void testFactoryMethod1() throws BadInputFormatException, InvalidDateException {
        assertEquals(
                new Deadline("deadline 1 /by 2020-10-10").toString(),
                Deadline.of("deadline 1 /by 2020-10-10").toString());
    }

    @Test
    public void testFactoryMethod2() throws InvalidDateException, BadInputFormatException {
        assertEquals(
                new Deadline("deadline 1 /by 2020-10-10").toString(),
                Deadline.of("deadline 1", Date.of("2020-10-10"), false).toString());
    }
}
