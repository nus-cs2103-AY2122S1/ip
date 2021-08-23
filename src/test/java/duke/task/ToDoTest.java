package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {
    @Test
    public void createTodo() {
        String desc = "TEST TODO";
        Task todo = ToDo.of(false, desc);
        assertEquals(desc, todo.getDescriptions());
        assertEquals(" ", todo.getStatusIcon());
        todo = ToDo.of(true, desc);
        assertEquals("X", todo.getStatusIcon());
    }

    @Test
    public void completeToDo() {
        String desc = "TEST TODO";
        Task todo = ToDo.of(false, desc);
        todo.done();
        assertEquals(true, todo.isDone());
    }

    @Test
    public void makeToDoStrings() {
        String desc = "TEST TODO";
        Task todo = ToDo.of(false, desc);
        assertEquals("[T][ ] TEST TODO", todo.toString());
        assertEquals("T|0|TEST TODO", todo.toDatabaseString());
        todo.done();
        assertEquals("[T][X] TEST TODO", todo.toString());
        assertEquals("T|1|TEST TODO", todo.toDatabaseString());
    }

}
