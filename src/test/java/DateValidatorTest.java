import duke.DateValidator;

import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;


public class DateValidatorTest {
    @Test
    public void DateTest() {
        DateValidator dateValidator =
                new DateValidator(DateTimeFormatter.ISO_LOCAL_DATE);

        assertTrue(dateValidator.isValid("2020-10-15"));
        assertFalse(dateValidator.isValid("2020-15-15"));
    }

}