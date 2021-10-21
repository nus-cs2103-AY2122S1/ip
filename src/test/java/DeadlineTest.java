import duke.Deadline;
import org.junit.jupiter.api.Test;


import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_RED = "\u001B[31m";

    @Test
    public void AddDeadlineTest() {
        try {
            LocalDate date = LocalDate.parse("2022-11-11");
            LocalTime time = LocalTime.parse("18:00");
            Deadline task = new Deadline("task1 ", date, time);
            assertEquals("[D][ ] task1 (By: Nov 11 2022 18:00)", task.toString());
            System.out.println("AddDeadlineTest: " + ANSI_GREEN + "pass" + ANSI_RESET);
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("AddDeadlineTest: " + ANSI_RED + "fail" + ANSI_RESET);
        }

    }

    @Test
    public void markDeadLineDoneTest() {
        try {
            LocalDate date = LocalDate.parse("2022-12-12");
            LocalTime time = LocalTime.parse("20:00");
            Deadline task = new Deadline("task2 ", date, time);
            assertEquals("[D][ ] task2 (By: Dec 12 2022 20:00)", task.toString());
            task.markAsDone();
            assertEquals("[D][X] task2 (By: Dec 12 2022 20:00)", task.toString());
            System.out.println("markDeadlineDoneTest: " + ANSI_GREEN + "pass" + ANSI_RESET);
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("markDeadlineDoneTest: " + ANSI_RED + "fail" + ANSI_RESET);
        }

    }
}
