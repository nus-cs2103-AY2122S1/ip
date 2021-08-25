import org.junit.jupiter.api.Test;
import duke.tasks.Todo;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void createTodoTest(){
        Todo event = new Todo("say hi");
        assertEquals("[T][ ] say hi", event.toString());
    }

    @Test
    public void completeTodoTest() {
        Todo event = new Todo("say hi");
        event.markAsDone();
        assertEquals("[T][X] say hi", event.toString());
    }
}