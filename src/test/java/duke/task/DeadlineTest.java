package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class DeadlineTest {

    @Test
    public void toStringTest() {
        LocalDate date = LocalDate.of(2021, 9, 06);
        assertEquals(new Deadline("sleep", date).toString(), "[D][ ] sleep (by: Sep 06 2021)");
    }

    @Test
    public void formatSaveTest() {
        LocalDate date = LocalDate.of(2021, 9, 06);
        Deadline deadline = new Deadline("return book", date);
        assertEquals(deadline.formatSave(), "D | 0 | return book | Sep 06 2021");
    }

}

