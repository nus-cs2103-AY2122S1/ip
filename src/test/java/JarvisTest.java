import jarvis.Deadline;
import jarvis.Event;
import jarvis.JarvisException;
import jarvis.Parser;
import jarvis.Todo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Class containing tests to check the correctness of the program.
 */
public class JarvisTest {

    /**
     * Checks if todo tasks are created correctly
     */
    @Test
    public void todoCreation_todoTask_success() {
        assertEquals(new Todo(" return book").toString(), "[T][ ] return book");
    }

    /**
     * Checks if event tasks are created correctly
     *
     * @throws JarvisException if the event date or timings are invalid
     */
    @Test
    public void eventCreation_eventTask_success() throws JarvisException {
        assertEquals(new Event(" return book", "23/08/2021 16:00-20:00").toString(),
                "[E][ ] return book (at: 23 Aug 2021 16:00 to 20:00)");
    }

    /**
     * Checks if deadline tasks are displayed correctly to users
     *
     * @throws JarvisException if the deadline date or timings are invalid
     */
    @Test
    public void deadlineCreation_deadlineTask_success() throws JarvisException {
        assertEquals(new Deadline(" return book", "23/08/2021 17:00").toString(),
                "[D][ ] return book (by: 23 Aug 2021 17:00)");
    }

    /**
     * Checks if deadline tasks are saved in the correct format in user's hard disk
     *
     * @throws JarvisException if the deadline date or timings are invalid
     */
    @Test
    public void deadlinePrintToFile_deadlineTask_success() throws JarvisException {
        assertEquals(new Deadline(" return book", "23/08/2021 17:00").toPrintToFile(),
                "[D][ ] return book (by: 23/08/2021 17:00)");
    }

    /**
     * Checks if Parse.parseCommand() works correctly
     */
    @Test
    public void parseCommand_doneTask_success() {
        assertEquals(Parser.parseCommand("done 1"), "done");
    }
}