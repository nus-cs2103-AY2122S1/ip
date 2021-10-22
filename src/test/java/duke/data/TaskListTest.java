package duke.data;

import static org.junit.jupiter.api.Assertions.assertEquals;

import duke.data.tasks.Deadlines;
import duke.data.tasks.ToDos;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;

public class TaskListTest {
    @Test
    public void emptyListSaveDataTest() {
        TaskList tasks = new TaskList();

        assertEquals("", tasks.getSaveData());
    }

    @Test
    public void oneTodoListSaveDataTest() {
        TaskList tasks = new TaskList();

        tasks.addToList(new ToDos("abc"));

        assertEquals("T~0~abc~", tasks.getSaveData());
    }

    @Test
    public void oneDeadlineListSaveDataTest() {
        TaskList tasks = new TaskList();

        tasks.addToList(new Deadlines("abc", LocalDate.parse("2021-06-13")));

        assertEquals("D~0~abc~2021-06-13~", tasks.getSaveData());
    }
}
