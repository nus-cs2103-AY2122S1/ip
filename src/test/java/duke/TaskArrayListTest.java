package duke;

import duke.tasks.Todo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertEquals;

class TaskArrayListTest {


    @Test
    void addTask_increaseSize() {
        TaskArrayList taskList = new TaskArrayList();
        assertEquals( 0,taskList.size());
        taskList.addTask(new Todo("ok"));
        assertEquals(1,taskList.size());
    }

    @Test
    void deleteTask_decreaseSize() {
    }

    @Test
    void enumerate() {
    }

    @Test
    void markDone() {
    }
}