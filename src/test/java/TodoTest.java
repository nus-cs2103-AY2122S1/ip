import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import task.Todo;

public class TodoTest {

    @Test
    public void getTask_newTask_success() {
        assertEquals(" hw",
                new Todo("todo hw").getTask());
    }

}
