import duke.task.Deadline;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
	@Test
	public void parsableDateTest() {
		Deadline task = new Deadline("finish week3 iP", "2021-08-24");
		assertEquals("[D][ ] finish week3 iP (by: Aug 24 2021)", task.toString());
	}

	@Test
	public void unParsableDateTest() {
		Deadline task = new Deadline("finish week3 iP", "week 3 tutorial class");
		assertEquals("[D][ ] finish week3 iP (by: week 3 tutorial class)", task.toString());
	}

	@Test
	public void markDoneTest() {
		Deadline task = new Deadline("finish week3 iP", "2021-08-24");
		task.markDone();
		assertEquals("[D][X] finish week3 iP (by: Aug 24 2021)", task.toString());
	}
}