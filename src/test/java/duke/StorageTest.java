package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.LocalDateTime;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.TaskList;
import duke.task.Todo;
import org.junit.jupiter.api.Test;


public class StorageTest {

    @Test
    public void saveTest() throws IOException {
        TaskList tasks = new TaskList();
        tasks.add(new Todo("Finish this week's Ip tasks"));
        tasks.add(new Deadline("CS2100 Tutorial", LocalDateTime.of(2021, 8, 24, 11, 0)));
        tasks.add(new Event("The Weekends", LocalDate.of(2021, 8, 28)));

        Storage storage = new Storage("data/test.txt");

        try {
            storage.save(tasks);
        } catch (DukeException e) {
            e.printStackTrace();
        }

        String expected = "T | 0 | Finish this week's Ip tasks\n"
                + "D | 0 | CS2100 Tutorial | Aug 24 2021 11.00AM\n"
                + "E | 0 | The Weekends | Aug 28 2021 All day\n";
        assertEquals(expected, Files.readString(Path.of("data/test.txt")));
    }
}
