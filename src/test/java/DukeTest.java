import duke.Event;
import duke.Duke;
import duke.Storage;
import duke.Ui;
import duke.TaskListInternal;
import duke.ToDo;
import duke.Deadline;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DukeTest {
    @Test
    public void fileLocationTest(){
        assertEquals(true,new Storage().checkExistence());
    }

    @Test
    public void welcomeTest(){
        assertEquals(new Ui().showWelcome(),"Welcome. I am your virtual assistant Duke. Sparkle up your day (TM).");
    }

    @Test
    public void initialiseTest(){
        assertEquals(true,new TaskListInternal().initialise(Duke.file,new Storage()));
    }

    @Test
    public void taskEqualsTest(){
        assertEquals(new Event("test","2020-10-10"),new Event("test","2020-10-10"));
        assertEquals(new Deadline("test","2020-10-10"),new Deadline("test","2020-10-10"));
        assertEquals(new ToDo("test"),new ToDo("test"));
    }
}
