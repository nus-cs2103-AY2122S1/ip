package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

public class UtilityTest {
    private final String str1 = "1997-02-02 1111";
    private final String str2 = "02 02 1997 | 1111";
    private final DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private final LocalDateTime dateTime = LocalDateTime.parse(str1, inputFormat);

    @Test
    public void stringToDate() {
        assertEquals(dateTime, new Utility().stringToDate(str1));
    }

    @Test
    public void dateToString() {
        assertEquals(str2, new Utility().dateToString(dateTime));
    }

    @Test
    public void dateToInputString() {
        assertEquals(str2, new Utility().dateToString(dateTime));
    }
}
