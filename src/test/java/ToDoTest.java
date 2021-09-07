import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import duke.task.ToDo;

class ToDoTest {
	@Test
	public void testCreateTodoTaskDisplaysCorrectly() {
		ToDo todoTask = new ToDo("work");
		assertEquals(todoTask.toString(), "[T][ ] work");
	}

	@Test
	public void testTodoTaskIsCompleted() {
		ToDo todoTask = new ToDo("work");
		todoTask.setDone(true);
		assertTrue(todoTask.isDone());
	}
}
