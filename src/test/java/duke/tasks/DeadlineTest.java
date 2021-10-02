package duke.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

class DeadlineTest {

    @Test
    void testToString() {
        DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("yyyy/MM/dd HHmm");
        LocalDateTime date = LocalDateTime.parse("2021/08/26 2359", inputFormat);
        Deadline ddl = new Deadline("complete ip", date);
        assertEquals("[D][ ] complete ip (by: 26 Aug 2021 2359)", ddl.toString());
    }

    @Test
    void testToSaveString() {
        DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("yyyy/MM/dd HHmm");
        LocalDateTime date = LocalDateTime.parse("2021/08/26 2359", inputFormat);
        Deadline ddl = new Deadline("complete ip", date);
        assertEquals("D|0|complete ip|26 Aug 2021 2359", ddl.toSaveString());
    }
}
