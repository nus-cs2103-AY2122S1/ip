import duke.DateValidator;
import java.time.format.DateTimeFormatter;
import org.junit.Test;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class DateValidatorTest {
    @Test
    public void DateTest() {
        DateValidator dateValidator = new DateValidator(DateTimeFormatter.ISO_LOCAL_DATE);

        assertTrue(dateValidator.isValid("2020-10-15"));
        assertFalse(dateValidator.isValid("2020-15-15"));
    }

}