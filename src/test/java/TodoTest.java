import duke.core.Duke;
import duke.task.Todo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TodoTest {
    @Test
    public void createTodoTest(){
        try {
            Duke duke = new Duke("data/todoList2.txt");
            Todo todo = new Todo("test", false);
        } catch (Exception e) {
            fail("Should not have thrown any exception");
        }


    }

    @Test
    public void searchKeywordTest(){
        try {
            Duke duke = new Duke("data/todoList2.txt");
            Todo todo = new Todo("test", false);
            todo.searchKeyword("run");
        } catch (Exception e) {
            fail("Should not have thrown any exception");
        }


    }
}
