import duke.task.Event;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
	@Test
	public void eventTaskTest() {
		Event task = new Event("finish week3 iP", "home");
		assertEquals("[E][ ] finish week3 iP (at: home)", task.toString());
	}

	@Test
	public void markDoneTest() {
		Event task = new Event("finish week3 iP");
		task.markDone();
		assertEquals("[E][X] finish week3 iP (at: home)", task.toString());
	}
}