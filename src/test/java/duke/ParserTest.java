package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ParserTest {
    @Test
    public void listTasks_noTasks_exceptionThrown() {
        TaskList taskList = new TaskList();
        Parser parser = new Parser(taskList);
        assertThrows(DukeException.class, parser::list);
    }

    @Test
    public void parse_newDeadline_taskAdded() throws DukeException {
        TaskList taskList = new TaskList();
        Parser parser = new Parser(taskList);
        TaskList expectedTaskList = new TaskList();
        LocalDateTime by = LocalDateTime.parse("2021-09-04 14:00", DateTimeFormatter.ofPattern("yyyy-MM-d H:mm"));
        Deadline deadline = new Deadline("lab 1", by);
        expectedTaskList.add(deadline);
        assertEquals(expectedTaskList.toString(), parser.parse("deadline lab 1 /by 2021-09-04 14:00").toString());
    }
}
