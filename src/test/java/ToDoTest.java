import duke.task.ToDo;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {
	@Test
	public void todoTaskTest() {
		ToDo task = new ToDo("finish week3 iP");
		assertEquals("[T][ ] finish week3 iP", task.toString());
	}

	@Test
	public void markDoneTest() {
		ToDo task = new ToDo("finish week3 iP");
		task.markDone();
		assertEquals("[T][X] finish week3 iP", task.toString());
	}
}