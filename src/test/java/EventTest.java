import duke.Event;
import org.junit.jupiter.api.Test;


import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_RED = "\u001B[31m";

    @Test
    public void AddEventTest() {
        try {
            LocalDate startDate = LocalDate.parse("2021-11-11");
            LocalTime startTime = LocalTime.parse("20:00");
            LocalDate endDate = LocalDate.parse("2022-11-11");
            LocalTime endTime = LocalTime.parse("18:00");
            Event task = new Event("task1 ", startDate, startTime, endDate, endTime);
            assertEquals("[E][ ] task1 (Start: Nov 11 2021 20:00 End: Nov 11 2022 18:00)", task.toString());
            System.out.println("AddEventTest: " + ANSI_GREEN + "pass" + ANSI_RESET);
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("AddEventTest: " + ANSI_RED + "fail" + ANSI_RESET);
        }

    }

    @Test
    public void markEventTest() {
        try {
            LocalDate startDate = LocalDate.parse("2021-11-11");
            LocalTime startTime = LocalTime.parse("20:00");
            LocalDate endDate = LocalDate.parse("2022-11-11");
            LocalTime endTime = LocalTime.parse("18:00");
            Event task = new Event("task2 ", startDate, startTime, endDate, endTime);
            assertEquals("[E][ ] task2 (Start: Nov 11 2021 20:00 End: Nov 11 2022 18:00)", task.toString());
            task.markAsDone();
            assertEquals("[E][X] task2 (Start: Nov 11 2021 20:00 End: Nov 11 2022 18:00)", task.toString());
            System.out.println("markDeadlineDoneTest: " + ANSI_GREEN + "pass" + ANSI_RESET);
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("markDeadlineDoneTest: " + ANSI_RED + "fail" + ANSI_RESET);
        }

    }
}
