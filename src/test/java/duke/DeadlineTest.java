package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;


public class DeadlineTest {
    private Deadline test = new Deadline("Homework",
            LocalDateTime.parse("2021-08-24 23:59", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));

    @Test
    public void testing(){
        assertEquals(test.toString(), "[D][ ] Homework (by: Aug 24 2021 23:59)");
    }
}
