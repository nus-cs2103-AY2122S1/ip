import duke.Duke;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class DukeTest {
    @Test
    public void run() {
        try {
            new Duke("data/tasks.txt").run();
        } catch (Exception e) {
            System.err.println(e.getMessage());
            fail();
        }
    }
}