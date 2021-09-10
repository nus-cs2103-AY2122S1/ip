import duke.Command;
import duke.Deadline;
import duke.Task;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DateTimeTest {

    @Test
    public void checkDateTimeWorking() {
        LocalDateTime firstDeadlineTime = LocalDateTime.of(2000, 10, 3, 23, 0);
        Deadline firstDeadline = new Deadline("firstDateline", firstDeadlineTime);

        ArrayList<String> list = new ArrayList<>();
        Collections.addAll(list = new ArrayList<String>(), "This", "Is", "For", "Testing");
        Command firstCommand = new Command(Task.TaskType.DEADLINE, list, "This is for testing", firstDeadlineTime);

        assertEquals(firstDeadline.getDeadline(), firstCommand.getLocalDateTime());
    }
}
