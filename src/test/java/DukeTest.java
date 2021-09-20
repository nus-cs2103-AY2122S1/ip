import static duke.Parser.parse;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import duke.Duke;
import duke.task.Deadline;
import duke.task.Task;

public class DukeTest {

    @Test
    public void deadlineStorageStringTest() {
        Task t = new Deadline("task 1", "2021-08-29", 0);
        Assertions.assertEquals("D | 0 | task 1 | 2021-08-29\n", t.toStorageString());
    }

    @Test
    public void deadlineToStringTest() {
        Task t = new Deadline("task 1", "2021-08-29", 0);
        Assertions.assertEquals("[D][ ] task 1 (by: 2021-08-29)", t.toString());
    }

    @Test
    public void parserTest() {
        Assertions.assertEquals(parse("list"), Duke.RequestType.DEFAULT);
        Assertions.assertEquals(parse("done 2"), Duke.RequestType.DONE);
        Assertions.assertEquals(parse("delete 1"), Duke.RequestType.DELETE);
        Assertions.assertEquals(parse("deadline task1 /by 2021-08-09"), Duke.RequestType.DEADLINE);
        Assertions.assertEquals(parse("event swimming /at 5:30-6:30"), Duke.RequestType.EVENT);
        Assertions.assertEquals(parse("todo buy groceries"), Duke.RequestType.TODO);
        Assertions.assertEquals(parse("bybye"), Duke.RequestType.UNUSUAL);

    }

}
