package tasks;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {
    @Test
    public void tagString_noTags_success() {
        Task task = new Task("Dummy description", true);
        assertEquals(task.getSaveText(), "T | 1 | Dummy description | \n");
    }

    @Test
    public void tagString_ThreeTags_success() {
        String[] tags = {"urgent", "customer", "group1"};
        Task task = new Task("Dummy description", true, tags);
        assertEquals(task.getSaveText(), "T | 1 | Dummy description | urgent, customer, group1\n");
    }
}
