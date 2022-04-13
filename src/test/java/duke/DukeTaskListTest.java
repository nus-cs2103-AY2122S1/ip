package duke;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.LocalTime;

public class DukeTaskListTest {
    DukeTaskList dukeTaskList = new DukeTaskList();

    @Test
    public void sendListToFileTest() {
        dukeTaskList.addToDo("buy milk");
        dukeTaskList.addDeadline("submit report",
            LocalDate.parse("2021-08-25"),
            LocalTime.parse("14:00"));
        dukeTaskList.addEvent("tutorial",
            LocalDate.parse("2021-08-25"),
            LocalTime.parse("14:00"),
            LocalTime.parse("16:00"));

        assertEquals("T|0|buy milk\n"
            + "D|0|submit report|2021-08-25|14:00\n"
            + "E|0|tutorial|2021-08-25|14:00|16:00\n",
            dukeTaskList.sendListToFile());
    }
}
