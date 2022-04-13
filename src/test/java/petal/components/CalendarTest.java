package petal.components;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import petal.task.Deadline;
import petal.task.Task;

public class CalendarTest {

    private final Calendar calendar = new Calendar();

    @BeforeEach
    public void beforeEach() {
        Deadline deadline = new Deadline("go for a run", "2/12/2021 1900", false);
        calendar.addToCalendar(deadline);
    }

    @Test
    public void addTimeable_deadline_noOutput() {
        Deadline deadline = new Deadline("go for a run", "2/12/2021 1900", false);
        calendar.addToCalendar(deadline);
    }

    @Test
    public void showTaskOnDate_noInput_tasksOnThisDate() {
        LocalDate localDate = LocalDate.parse("2021-12-02");
        String tasks = calendar.showTasksOnDate(localDate);
        assertEquals("Here are the tasks on this date: "
                + "\n1. [D][ ] Go for a run (by: December 2, 2021 19:00)", tasks);
    }

    @Test
    public void updateCalendar_listOfTasks_noOutput() {
        List<Task> list = new ArrayList<>();
        Deadline deadline1 = new Deadline("go", "2/12/2021 1900", false);
        list.add(deadline1);
        calendar.updateCalendar(list);
    }
}
