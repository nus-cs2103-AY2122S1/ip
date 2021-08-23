import duke.Deadline;
import duke.Event;
import duke.Parser;
import duke.Todo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DukeTest {
    @Test
    public void todoCreation_todoTask_success(){
        assertEquals(new Todo(" return book").toString(), "[T][ ] return book");
    }

    @Test
    public void eventCreation_eventTask_success(){
        assertEquals(new Event(" return book", "23/08/2021 17:00").toString(),
                "[E][ ] return book (at: 23 Aug 2021 17:00)");
    }

    @Test
    public void deadlineCreation_deadlineTask_success(){
        assertEquals(new Deadline(" return book", "23/08/2021 17:00").toString(),
                "[D][ ] return book (by: 23 Aug 2021 17:00)");
    }

    @Test
    public void deadlinePrintToFile_deadlineTask_success(){
        assertEquals(new Deadline(" return book", "23/08/2021 17:00").toPrintToFile(),
                "[D][ ] return book (by: 23/08/2021 17:00)");
    }

    @Test
    public void parseCommand_doneTask_success() {
        assertEquals(Parser.parseCommand("done 1"), "done");
    }
}