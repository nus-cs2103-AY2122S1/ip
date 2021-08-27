package duke.tasks;

import main.java.duke.tasks.Deadline;
import org.junit.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.Assert.assertEquals;

public class DeadlineTest {
    @Test
    public void fromStringToDate_correct_success() {
        Deadline actual = new Deadline("complete 2105 assignment", " 2021/08/28");
        LocalDate actualDate = actual.getDateFormatted();
        LocalDate expectedDate = LocalDate.parse("2021/08/28", DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        assertEquals(actualDate, expectedDate);
    }

    @Test
    public void toString_correct_success() {
        Deadline actual = new Deadline("jogging", " 2020/09/13");
        String actualString = actual.toString();
        String expectedString = "[D] [ ] jogging:2020-09-13";
        assertEquals(actualString, expectedString);
    }
}