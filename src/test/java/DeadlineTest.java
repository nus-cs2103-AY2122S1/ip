import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import duke.task.Deadline;

public class DeadlineTest {
	@Test
	public void testDeadlineDisplayedCorrectly() {
		String datetime = "2021-08-27";
		LocalDate deadline = LocalDate.parse(datetime);
		String taskName = "finish ip project";
		Deadline deadlineTask = new Deadline(taskName, deadline);
		String expectedResult = String.format("[D][ ] %s (by: Aug 27 2021)", taskName);
		assertEquals(expectedResult, deadlineTask.toString());
	}

	@Test
	public void testDeadlineTaskIsCompleted() {
		String datetime = "2021-08-27";
		LocalDate deadline = LocalDate.parse(datetime);
		String taskName = "finish ip project";
		Deadline deadlineTask = new Deadline(taskName, deadline);
		deadlineTask.setDone(true);
		assertTrue(deadlineTask.isDone());
	}
}
