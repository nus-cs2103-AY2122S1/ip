package duke;

import duke.Tasks.Deadline;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {

    public static DateTimeFormatter validFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    @Test
    public void markDoneTest() {
        Deadline d = new Deadline("return book", LocalDateTime.parse("2021-08-20 12:00", validFormat), false);
        assertEquals(d.markDone().toString(), "[D][X] return book (by: Aug 20 2021 12:00)");
    }

    @Test
    public void fileFormatetedStringTest() {
        Deadline d = new Deadline("CS2103T due", LocalDateTime.parse("2021-08-22 17:19", validFormat), false);
        assertEquals(d.changeFormat(), "D | 0 | CS2103T due | 2021-08-22 17:19");
    }

}