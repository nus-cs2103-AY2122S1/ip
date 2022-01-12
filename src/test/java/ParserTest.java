import duke.parser.Parser;
import duke.exception.DukeException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParserTest {
	@Test
	public void unknownCommandExceptionTest() {
		assertThrows(DukeException.class, () -> new Parser().parse("unknown command"));
	}

	@Test
	public void eventTaskExceptionTest() {
		assertThrows(DukeException.class, () -> new Parser().parse("event e"));
	}

	@Test
	public void deadlineTaskExceptionTest() {
		assertThrows(DukeException.class, () -> new Parser().parse("deadline d"));
	}
}
