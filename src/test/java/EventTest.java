import Duke.Event;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_RED = "\u001B[31m";

    @Test
    public void AddEventTest() {
        try {
            Event task = new Event("task1 ", "loc1");
            assertEquals("[E][ ] task1 --(at: loc1)", task.toString());
            System.out.println("AddEventTest: " + ANSI_GREEN + "pass" + ANSI_RESET);
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("AddEventTest: " + ANSI_RED + "fail" + ANSI_RESET);
        }

    }

    @Test
    public void markEventTest() {
        try {
            Event task = new Event("task2 ", "loc2");
            assertEquals("[E][ ] task2 --(at: loc2)", task.toString());
            task.markAsDone();
            assertEquals("[E][X] task2 --(at: loc2)", task.toString());
            System.out.println("markDeadlineDoneTest: " + ANSI_GREEN + "pass" + ANSI_RESET);
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("markDeadlineDoneTest: " + ANSI_RED + "fail" + ANSI_RESET);
        }

    }
}
