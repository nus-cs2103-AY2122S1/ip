package duke.task;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    @Test
    public void tasksWithinMonthOrDay_Month_success() {
        Deadline deadline = new Deadline("test deadline", "7/9/2021");
        Event event = new Event("test event", "17/9/2021");
        ArrayList<Task> tasksForDay = new ArrayList<>();
        tasksForDay.add(deadline);
        TaskList dayList = new TaskList(tasksForDay);
        String expectedDay = dayList.toString();
        ArrayList<Task> tasksForMonth = new ArrayList<>();
        tasksForMonth.add(event);
        TaskList monthList = new TaskList(tasksForMonth);
        String expectedMonth = monthList.toString();
        ArrayList<Task> testList = new ArrayList<>();
        testList.add(deadline);
        testList.add(event);
        testList.add(new Event("not", "11/11/1111"));
        testList.add(new Event("also not", "7/9/1177 1655"));
        TaskList currListExpected = new TaskList(testList);
        String actualDay = currListExpected.tasksWithinMonthOrDay("day").toString();
        String actualMonth = currListExpected.tasksWithinMonthOrDay("month").toString();
        assertEquals(expectedDay, actualDay);
        assertEquals(expectedMonth, actualMonth);
    }
}
