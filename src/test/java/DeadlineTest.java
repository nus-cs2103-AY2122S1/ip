import org.junit.jupiter.api.Test;
import duke.tasks.Todo;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {

    @Test
    public void createTodoTest(){
        Todo td = new Todo("todo helloworld");
        assertEquals("[T][ ] helloworld", td.toString());
    }

    @Test
    public void completeTodoTest() {
        Todo td = new Todo("todo helloworld");
        td.markAsDone();
        assertEquals("[T][X] helloworld", td.toString());
    }
}