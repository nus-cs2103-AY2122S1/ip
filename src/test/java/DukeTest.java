import duke.Duke;
import duke.Task;
import duke.exception.DukeException;
import duke.exception.EmptyDescException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class DukeTest {
    @Test
    public void getResponse_invalidInput_exceptionMessage() {
        Duke d = new Duke("myTasks.txt");
        String response = d. getResponse("wrong");
        assertTrue(response.contentEquals(new DukeException().getMessage()));
    }

    @Test
    public void getResponse_emptyDescription_exceptionMessage() {
        Duke d = new Duke("myTasks.txt");
        String response = d. getResponse("todo");
        assertTrue(response.contentEquals(new EmptyDescException(Task.Type.TODO).getMessage()));
    }
}
