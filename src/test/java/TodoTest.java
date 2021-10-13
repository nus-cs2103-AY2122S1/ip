import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import duke.core.Duke;
import duke.task.Todo;


public class TodoTest {
    @Test
    public void createTodoTest() {
        try {
            Duke duke = new Duke("data/todoList2.txt");
            Todo todo = new Todo("test", false);
        } catch (Exception e) {
            fail("Should not have thrown any exception");
        }


    }

    @Test
    public void searchKeywordTest() {
        try {
            Duke duke = new Duke("data/todoList2.txt");
            Todo todo = new Todo("test", false);
            todo.searchKeyword("run");
        } catch (Exception e) {
            fail("Should not have thrown any exception");
        }


    }
}
