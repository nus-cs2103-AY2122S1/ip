import tokio.exceptions.DukeException;
import org.junit.jupiter.api.Test;
import tokio.tasks.Deadlines;
import tokio.tasks.Events;
import tokio.tasks.Todos;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TasksTest {
    @Test
    public void addTodosTest() {
        Todos userInput1 = new Todos("homework");
        assertEquals("[T][ ] homework", userInput1.formatToStorage());
        
        Todos userInput2 = new Todos("CS2103T tutorial");
        userInput2.setDone();
        assertEquals("[T][X] CS2103T tutorial", userInput2.formatToStorage());
    }
    
    @Test
    public void addDeadlinesTest() throws DukeException {
        Deadlines userInput3 = new Deadlines("project submission", "2021-09-23");
        assertEquals("[D][ ] project submission (by: Sep 23 2021)", userInput3.formatToStorage());

        Deadlines userInput4 = new Deadlines("CS2103T assignment 1 submission", "2021-10-10");
        userInput4.setDone();
        assertEquals("[D][X] CS2103T assignment 1 submission (by: Oct 10 2021)", userInput4.formatToStorage());
    }
    
    @Test
    public void addEventsTest() throws DukeException {
        Events userInput5 = new Events("online recital", "2021-09-01", "18:00");
        assertEquals("[E][ ] online recital (at: Sep 01 2021 18:00)", userInput5.formatToStorage());

        Events userInput6 = new Events("NYE party!!!", "2021-12-31", "23:30");
        userInput6.setDone();
        assertEquals("[E][X] NYE party!!! (at: Dec 31 2021 23:30)", userInput6.formatToStorage());
    }
}
