package duke;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class DukeTest {
    @Test
    public void dummyTest() {
        assertEquals(2, 2);
    }

    @Test
    public void initialTaskStatusTest() {
        Todo item = new Todo("Mop floor");
        assertEquals(" ", item.getStatusIcon());
    }

    @Test
    public void completeTaskTest() {
        Todo item = new Todo("Mop floor");
        item.complete();
        assertEquals("X", item.getStatusIcon());
    }

    @Test
    public void saveDeadlineTest() {
        Deadline task = new Deadline("Eat cake", "2020-04-03");
        assertEquals("D |   | Eat cake | 2020-04-03", task.saveData());
    }

    @Test
    public void deadlineToStringTest() {
        Deadline task = new Deadline("Birthday", "2020-01-15");
        task.complete();
        assertEquals("[D][X] Birthday (by: Jan 15 2020)", task.toString());
    }
}
