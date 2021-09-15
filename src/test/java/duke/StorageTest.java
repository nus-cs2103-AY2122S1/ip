package duke;

import duke.task.Deadline;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import duke.task.ToDo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StorageTest {

    @Test
    public void testParseLineToDo() {
        Storage s = new Storage("data/tasks.txt");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMM yyyy HH:mm");

        assertEquals(s.parseLine("T | 1 | read book"),
                new ToDo("read book", true));
    }

    @Test
    public void testParseLineDeadline() {
        Storage s = new Storage("data/tasks.txt");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMM yyyy HH:mm");

        assertEquals(s.parseLine("D | 0 | return book | 31 Aug 2021 09:00"),
                new Deadline("return book", false,
                        LocalDateTime.parse("31 Aug 2021 09:00", formatter)));
    }
}
