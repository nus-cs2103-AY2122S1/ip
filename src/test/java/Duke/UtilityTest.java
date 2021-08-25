import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.time.LocalDateTime;

public class UtilityTest {
    @Test
    public void parseDateTest() {
        assertEquals(LocalDateTime.of(2021, 8, 25, 12, 0), Utility.parseDate("25/08/2021 1200"));
    }

    @Test
    public void dateToStringTest() {
        assertEquals("Aug 25 2021, 12:00", Utility.dateToString(LocalDateTime.of(2021, 8, 25, 12, 0)));
    }

    @Test
    void dateToFileTest() {
        assertEquals("25/08/2021 1200", Utility.dateToFile(LocalDateTime.of(2021, 8, 25, 12, 0)));
    }
}
