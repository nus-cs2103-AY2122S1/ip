import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

import duke.Deadline;
import duke.HelpCommand;
import duke.Todo;

public class DukeTest {

    /**
     * To test the creation and writing of the todo class. Checks if to string is correct
     */
    @Test
    public void todoTest() {
        Todo todoDummy = new Todo("Test dummy");
        String incompleteTodoDummyString = "[T][X] Test dummy";

        //Test incomplete dummy with output string result
        assertEquals(incompleteTodoDummyString, todoDummy.toString());

        //Test completed dummy with output string result
        todoDummy.completeTask();
        String completedTodoDummyString = "[T][done] Test dummy";
        assertEquals(completedTodoDummyString, todoDummy.toString());
    }

    /**
     * To test the creation and string representation of deadline class
     */
    @Test
    public void deadlineTest() {
        LocalDateTime testDeadlineDateTime = LocalDateTime.of(2020, 2, 28, 23, 00);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        Deadline deadlineDummy = new Deadline("Test deadline", testDeadlineDateTime);
        assertEquals("[D][X] Test deadline 28/02/2020 23:00", deadlineDummy.toString());

    }

    @Test
    public void helpCommandTest() {
        assertEquals(HelpCommand.getCommand(0), HelpCommand.getCommand("generalHelp"));
    }
}
