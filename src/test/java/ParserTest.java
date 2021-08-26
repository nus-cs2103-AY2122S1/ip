import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import duke.Deadline;
import duke.Event;
import duke.Parser;
import duke.Task;
import duke.TaskType;
import duke.Todo;

public class ParserTest {
    @Test
    void parseTaskLine_todoInput_correctTodo() {
        String description = "le description lol";
        Task todo = Parser.parseTaskLine(description, TaskType.TODO);
        assert(todo instanceof Todo);
        assertEquals(description, todo.getDescription());
    }

    @Test
    void parseTaskLine_deadlineInput_correctDeadline() {
        String description = "le description lol";
        String date = "2021-08-06";

        Task task = Parser.parseTaskLine(
            String.format("%s /by %s", description, date),
            TaskType.DEADLINE
        );
        assert(task instanceof Deadline);

        Deadline deadline = (Deadline) task;
        assertEquals(description, deadline.getDescription());
        assertEquals(LocalDate.of(2021, 8, 6), deadline.getDeadline());
    }

    @Test
    void parseTaskLine_eventInput_correctEvent() {
        String description = "le description lol";
        String date = "2021-08-06";

        Task task = Parser.parseTaskLine(
            String.format("%s /at %s", description, date),
            TaskType.EVENT
        );
        assert(task instanceof Event);

        Event event = (Event) task;
        assertEquals(description, event.getDescription());
        assertEquals(LocalDate.of(2021, 8, 6), event.getTime());
    }
}
