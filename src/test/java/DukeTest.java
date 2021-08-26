import duke.Duke;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DukeTest {
    @Test
    public void dummyTest(){
        Duke fml = new Duke("myTasks.txt");
        assertEquals(2, 2);
    }

    @Test
    public void dummyTest2(){
        assertEquals(2, 3);
    }
}
