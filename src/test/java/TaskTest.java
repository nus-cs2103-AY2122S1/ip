import duke.tasks.DateParser;
import duke.tasks.Task;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {
    @Test
    public void eventTask() {
        Task event = Task.createTask("Find magic potion", Task.Type.TODO);
        Assertions.assertEquals(
                "[T] [ ] Find magic potion",
                event.toString()
        );
    }

    @Test
    public void eventTaskDB() {
        Task event = Task.createTask("Find magic potion", Task.Type.TODO);
        Assertions.assertEquals(
                "false--|--T--|--Find magic potion--|--",
                event.taskToString()
        );
    }

    @Test
    public void testExceptionDate() {
        LocalDateTime x = DateParser.parseDateTimeInput("2020-04-01 0500");
        Assertions.assertEquals(
                "2020-04-01 0500",
                DateParser.toDatabaseFormat(x)
        );
        Assertions.assertEquals(
                "01 Apr 2020, 05:00 AM",
                DateParser.toHumanReadable(x)
        );
    }

}