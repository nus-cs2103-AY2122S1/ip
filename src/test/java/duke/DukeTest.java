package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DukeTest {
    @Test
    public void initialTaskList_createNewDuke_zeroTasksInList() {
        Duke d = new Duke();
        assertEquals(d.getNumberOfTasks(), 0);
    }

}
