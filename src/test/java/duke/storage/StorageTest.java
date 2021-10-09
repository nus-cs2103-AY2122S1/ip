package duke.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class StorageTest {
    @Test
    public void transformToData_normalTasks_success() {
        String[] tasks = {"[D][X] deadlineDone (by: today)", "[T][ ] todo",
            "[E][ ] event (at: 16.40)", "[E][X] eventDone (at: now)"};

        String[] formats = {"D | 1 | deadlineDone ~ today", "T | 0 | todo",
            "E | 0 | event ~ 16.40", "E | 1 | eventDone ~ now"};
        int count = 0;
        for (String task : tasks) {
            String result = Storage.transformToData(task);
            assertEquals(formats[count++], result);
        }
    }
}
