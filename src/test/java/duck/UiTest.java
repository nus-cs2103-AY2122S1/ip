package duck;

import duck.task.Deadline;
import duck.task.Task;
import duck.task.Todo;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UiTest {

    private final Ui ui = new Ui();
    @Test
    public void testDisplayList() {
        ArrayList<Task> taskList = new ArrayList<>();
        taskList.add(new Todo("Help cat cross the street"));
        taskList.add(new Deadline("Do homework", LocalDate.parse("2020-11-11")));

        String expectedOutput = "Here are the tasks in your list:" + "\n"
                + "1.[T][ ] Help cat cross the street" + "\n"
                + "2.[D][ ] Do homework (by Nov 11 2020)" + "\n";

        assertEquals(expectedOutput, ui.displayList(taskList));
    }

    @Test
    public void testShowSchedule() {
        
        String desiredDate = "2021-09-17";
        ArrayList<Task> taskList = new ArrayList<>();

        taskList.add(new Deadline("CS2103T iP Final Submission", LocalDate.parse("2021-09-17")));
        taskList.add(new Deadline("CS2101 OP1 Reflections",
                LocalDate.parse("2021-09-17"), LocalTime.parse("23:00")));
        taskList.add(new Deadline("Watch CS2100 Lectures", LocalDate.parse("2021-09-17")));

        String expectedOutput = "Here is the schedule for 2021-09-17:" + "\n"
                + "[D][ ] CS2101 OP1 Reflections (by Sep 17 2021 at 11:00 PM)" + "\n"
                + "\n"
                + "Here are tasks without specific timing:" + "\n"
                + "[D][ ] CS2103T iP Final Submission (by Sep 17 2021)" + "\n"
                + "[D][ ] Watch CS2100 Lectures (by Sep 17 2021)" + "\n";

        assertEquals(expectedOutput, ui.displaySchedule(taskList, LocalDate.parse(desiredDate)));
    }
}
