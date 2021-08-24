package duke;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TodoTest {

    Todo todo;

    @Test
    public void dummyTest(){
        assertEquals(2, 2);
    }

    @Test
    public void createTodo() {
        todo = new Todo("Description");
    }

    @Test
    public void toStringTest() {
        todo = new Todo("Description");
        assertEquals("[T][ ] Description", todo.toString());
    }
}
