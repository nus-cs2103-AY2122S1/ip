import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.ToDo;

public class ToDoTest {
    @Test
    public void printTest() {
        String task = "read book";
        ToDo toDoTest1 = new ToDo(task);
        String printTaskActual = toDoTest1.printTask();
        String printTaskExpected = "[T][ ] read book";
        assertEquals(printTaskExpected, printTaskActual);
    }
}
