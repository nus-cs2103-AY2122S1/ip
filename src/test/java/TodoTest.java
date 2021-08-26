import duke.task.ToDo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


class ToDoTest {
    @Test
    public void testCreateTodoTask(){
        ToDo todoTask = new ToDo("work");
        assertEquals(todoTask.toString(), "[T][ ] work");
    }

	@Test
	public void testTodoTaskIsCompleted(){
		ToDo todoTask = new ToDo("work");
		todoTask.setDone(true);
		assertTrue(todoTask.isDone());
	}
}