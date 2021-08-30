package bruh.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class LocalDateTimeOrStringTest {
    @Test
    public void testDescription() {
        assertEquals("25 Dec 1999, 1:00 PM", new LocalDateTimeOrString("25/12/1999 1300").toString());
        assertEquals("25 Dec 1999, 1:00 PM", new LocalDateTimeOrString("25-12-1999 1300").toString());
        assertEquals("Improperly formatted date", new LocalDateTimeOrString("Improperly formatted date").toString());
        assertEquals("", new LocalDateTimeOrString("").toString());
    }
}
