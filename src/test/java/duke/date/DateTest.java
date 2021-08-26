package duke.date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import duke.exception.InvalidDateException;

public class DateTest {
    @Test
    public void testStringConversion() {
        assertEquals("Oct 10 2020", new Date(LocalDate.parse("2020-10-10")).toString());
    }

    @Test
    public void testJsonConversion() {
        assertEquals("2020-10-10", new Date(LocalDate.parse("2020-10-10")).toJsonString());
    }

    @Test
    public void testEquals() {
        assertEquals(new Date(LocalDate.parse("2020-10-10")), new Date(LocalDate.parse("2020-10-10")));
    }

    @Test
    public void testNotEquals() {
        assertNotEquals(new Date(LocalDate.parse("2020-10-10")), new Date(LocalDate.parse("2020-10-11")));
    }

    @Test
    public void testFactoryMethod() throws InvalidDateException {
        assertEquals("Oct 10 2020", Date.of("2020-10-10").toString());
    }

    @Test
    public void testInvalidDate() {
        assertThrows(InvalidDateException.class, () -> Date.of("10-10-2020"));
    }
}
