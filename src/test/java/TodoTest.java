import duke.Todo;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_RED = "\u001B[31m";

    @Test
    public void AddTodoTest() {
        try {
            Todo task = new Todo("task1");
            assertEquals("[T][ ] task1", task.toString());
            System.out.println("AddTodoTest: " + ANSI_GREEN + "pass" + ANSI_RESET);
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("AddTodoTest: " + ANSI_RED + "fail" + ANSI_RESET);
        }

    }

    @Test
    public void markTodoDoneTest() {
        try {
            Todo task = new Todo("task2");
            assertEquals("[T][ ] task2", task.toString());
            task.markAsDone();
            assertEquals("[T][X] task2", task.toString());
            System.out.println("markTodoDoneTest: " + ANSI_GREEN + "pass" + ANSI_RESET);
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("markTodoDoneTest: " + ANSI_RED + "fail" + ANSI_RESET);
        }

    }
}
