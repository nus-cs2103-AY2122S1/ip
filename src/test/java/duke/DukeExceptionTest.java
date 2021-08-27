package duke;
import org.junit.Test;
import org.junit.Assert;

public class DukeExceptionTest {
    @Test
    public void getMessageTest() {
        Assert.assertEquals("OOPS! I'm sorry, but I don't know that command", new DukeException("OOPS! I'm sorry, but I don't know that command").getMessage());
    }
}