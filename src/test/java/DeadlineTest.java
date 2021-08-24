import Duke.Deadline;
import org.junit.jupiter.api.Test;


import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_RED = "\u001B[31m";

    @Test
    public void AddDeadlineTest() {
        try {
            LocalDate date = LocalDate.parse("2021-11-11");
            Deadline task = new Deadline("task1 ", date);
            assertEquals("[D][ ] task1 --(by: Nov 11 2021)", task.toString());
            System.out.println("AddDeadlineTest: " + ANSI_GREEN + "pass" + ANSI_RESET);
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("AddDeadlineTest: " + ANSI_RED + "fail" + ANSI_RESET);
        }

    }

    @Test
    public void markDeadLineDoneTest() {
        try {
            LocalDate date = LocalDate.parse("2021-12-12");
            Deadline task = new Deadline("task2 ", date);
            assertEquals("[D][ ] task2 --(by: Dec 12 2021)", task.toString());
            task.markAsDone();
            assertEquals("[D][X] task2 --(by: Dec 12 2021)", task.toString());
            System.out.println("markDeadlineDoneTest: " + ANSI_GREEN + "pass" + ANSI_RESET);
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("markDeadlineDoneTest: " + ANSI_RED + "fail" + ANSI_RESET);
        }

    }
}
