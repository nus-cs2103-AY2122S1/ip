package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LocalDateTimeOrStringTest {
    @Test
    public void testDescription() {
        assertEquals("25 Dec 1999, 1:00 PM", new LocalDateTimeOrString("25/12/1999 1300").getDesc());
        assertEquals("25 Dec 1999, 1:00 PM", new LocalDateTimeOrString("25-12-1999 1300").getDesc());
        assertEquals("Improperly formatted date", new LocalDateTimeOrString("Improperly formatted date").getDesc());
        assertEquals("", new LocalDateTimeOrString("").getDesc());
    }
}
