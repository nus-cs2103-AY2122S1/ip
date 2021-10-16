package duke.operation;

import duke.exception.DukeException;
import duke.parser.Parser;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DeadlineTest {
	private final DateTimeFormatter dateTimeFormatterFrom =
			DateTimeFormatter.ofPattern("yyyy-dd-MM HH:mm");
	private final Deadline deadline = new Deadline("cs2103t",
			LocalDateTime.parse("2020-02-02 02:02", dateTimeFormatterFrom), false);
	private Parser parser = new Parser();

	@Test
	public void splitDeadlineTest() throws DukeException {
		assertEquals(deadline.toString(),
				parser.splitDeadline("deadline cs2103t/by 2020-02-02 02:02").toString());
	}
}
