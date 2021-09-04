package duke;

import org.junit.jupiter.api.Test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {

    @Test
    public void parseDateTimeTest() {
        LocalDate dateTime = LocalDate.parse("2019-10-15");
        String result = dateTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        assertEquals("Oct 15 2019", result);
    }

}
