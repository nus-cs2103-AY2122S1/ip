import duke.core.Duke;
import duke.task.Deadline;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class DeadlineTest {
    @Test
    public void createDeadlineTest(){
        try {
            Duke duke = new Duke("data/todoList2.txt");
            Deadline deadline = new Deadline("test", false, "/by 2020-11-03 4pm");
        } catch (Exception e) {
            fail("Should not have thrown any exception");
        }


    }

    @Test
    public void getTimeTest(){
        try {
            Duke duke = new Duke("data/todoList2.txt");
            Deadline deadline = new Deadline("test", false, "/by 2020-11-03 4pm");
            deadline.getTime();
        } catch (Exception e) {
            fail("Should not have thrown any exception");
        }


    }
}
