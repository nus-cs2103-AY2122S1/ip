import duke.Deadline;
import duke.Todo;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DukeTest {
    @Test
    public void TodoTest() {
        Todo todoDummy = new Todo("Test dummy");
        String incompleteTodoDummyString = "[T][X] Test dummy";

        //Test incomplete dummy with output string result
        assertEquals(incompleteTodoDummyString, todoDummy.toString());

        //Test completed dummy with output string result
        todoDummy.completeTask();
        String completedTodoDummyString = "[T][✓] Test dummy";
        assertEquals(completedTodoDummyString, todoDummy.toString());
    }


    @Test
    public void DeadlineTest() {
        LocalDateTime testDeadlineDateTime = LocalDateTime.of(2020, 2, 28, 23, 00);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        Deadline deadlineDummy = new Deadline("Test deadline", testDeadlineDateTime);
        assertEquals("[D][X] Test deadline 28/02/2020 23:00", deadlineDummy.toString());

    }
}
