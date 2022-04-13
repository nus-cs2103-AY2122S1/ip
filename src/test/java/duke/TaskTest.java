package duke;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TaskTest {
    @Test
    public void eventTest() {
        Event firstEvent = new Event("project meeting", "Aug 26 2021 19:15");
        Event secondEvent = new Event("project meeting", "Aug 26 2021 19:15");
        secondEvent.setDone();
        assertEquals(false, firstEvent.equals(secondEvent));
    }

    @Test
    public void deadlineTest() {
        Deadline firstDeadline = new Deadline("return book", "2021-08-27 18:00");
        Deadline secondDeadline = new Deadline("return book", "2021-08-27 18:00");
        assertEquals(true, firstDeadline.equals(secondDeadline));
    }

    @Test
    public void toDoTest() {
        ToDo firstToDo = new ToDo("todo borrow book");
        ToDo secondToDo = new ToDo("todo borrow book");
        assertEquals(true, firstToDo.equals(secondToDo));
    }

}
