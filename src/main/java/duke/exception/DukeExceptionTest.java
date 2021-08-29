package duke.exception;

import duke.Parser;
import org.junit.Test;
import org.junit.Assert;

public class DukeExceptionTest {
    @Test
    public void InvalidCommandException_exceptionObject_errorMessage() {
        Assert.assertEquals("OOPS!!! I'm sorry, but I don't know what that means :-(", new InvalidCommandException("OOPS!!! " +
                "I'm sorry, but I don't know what that means :-(").toString());
    }

    @Test
    public void EmptyDescriptionException_exceptionObject_errorMessage() {
        Assert.assertEquals("OOPS!!! The description of a deadline cannot be empty.", new InvalidCommandException("OOPS!!! " +
                "The description of a deadline cannot be empty.").toString());
    }

    @Test
    public void InvalidDateTimeException_exceptionObject_errorMessage() {
        Assert.assertEquals("The format of your command is incorrect! It should be deadline/by " + "<yyyy-mm-dd HHmm>", 
                new InvalidCommandException("The format of your command is incorrect! It should be deadline/by <yyyy-mm-dd HHmm>").toString());
    }
    
    
}