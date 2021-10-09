package duke;

import org.junit.Test;
import org.junit.Assert;

public class ParserTest {
    @Test
    public void isExit_commandBye_true() {
        Assert.assertEquals(true, new Parser("bye").isExit());
    }

    @Test
    public void isExit_commandEmptyString_true() {
        Assert.assertEquals(true, new Parser("").isExit());
    }

    @Test
    public void isExit_commandDeadline_false() {
        Assert.assertEquals(false, new Parser("deadline book ticket/by 2016-09-22 1700").isExit());
    }

    @Test
    public void parse_commandDeadline_success() {
        Assert.assertEquals("This is a deadline command", new Parser("deadline book ticket/by " 
                + "2016-09-22 1700").parse().toString());
    }

    @Test
    public void parse_commandEvent_success() {
        Assert.assertEquals("This is an event command", new Parser("event concert/at 2016-09-22 1900")
                .parse().toString());
    }

    @Test
    public void parse_commandTodo_success() {
        Assert.assertEquals("This is a todo command", new Parser("todo play cricket").parse().toString());
    }

    @Test
    public void parse_commandDelete_success() {
        Assert.assertEquals("This is a delete command", new Parser("delete 1").parse().toString());
    }

    @Test
    public void parse_commandDone_success() {
        Assert.assertEquals("This is a done command", new Parser("done 1").parse().toString());
    }

    @Test
    public void parse_commandInvalidString_success() {
        Assert.assertEquals("This is an invalid command", new Parser("Some invalid string")
                .parse().toString());
    }
}
