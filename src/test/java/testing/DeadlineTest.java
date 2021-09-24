package testing;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import duke.Duke;
import duke.exception.WrongCommandFormatException;
import duke.tasktype.Deadline;

public class DeadlineTest {


    @Test
    public void testGetTypeIcon() {
        try {
            Duke duke = new Duke();
            Assertions.assertEquals("[D]", new Deadline("run /by 2000-12-12", false).getTypeIcon());
        } catch (WrongCommandFormatException e) {
            throw new Error("case 1 failed");
        }

    }

    @Test
    public void testDeadlineConstructorException1() {
        Duke duke = new Duke();
        try {
            new Deadline("run /at 2000-12-12", false);
        } catch (WrongCommandFormatException e) {
            Assertions.assertEquals(
                    "Wrong keyword used. Please try again with /by",
                    e.getMessage()
            );
        }
    }

    @Test
    public void testDeadlineConstructorException2() {
        Duke duke = new Duke();
        try {
            new Deadline("run /by 20-12-12", false);
        } catch (WrongCommandFormatException e) {
            Assertions.assertEquals(
                    "Wrong deadline format specified. \n"
                            + "Current format setting: "
                            + Duke.getFormat()
                            + "\n"
                            + "Please try again or consider changing the format "
                            + "settings by using the command `setFormat`",
                    e.getMessage()
            );
        }
    }

}
