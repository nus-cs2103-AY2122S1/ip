package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {
    @Test
    public void getTaskTypeTest() {
        try {
            assertEquals(Task.TaskName.getTaskType("[T]"), Task.TaskName.TODO);
            assertEquals(Task.TaskName.getTaskType("todo"), Task.TaskName.TODO);
            assertEquals(Task.TaskName.getTaskType("[D]"), Task.TaskName.DEADLINE);
            assertEquals(Task.TaskName.getTaskType("deadline"), Task.TaskName.DEADLINE);
            assertEquals(Task.TaskName.getTaskType("[E]"), Task.TaskName.EVENT);
            assertEquals(Task.TaskName.getTaskType("event"), Task.TaskName.EVENT);
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void getStatusIconTest() {
        Task notDone = new Task("notDone",false);
        Task done = new Task("done",true);
        assertEquals(notDone.getStatusIcon(), " ");
        assertEquals(done.getStatusIcon(), "X");
    }

    @Test
    public void markDoneTest() {
        Task test = new Task("test",false);
        assertEquals(test.getStatusIcon(), " ");
        test.markDone();
        assertEquals(test.getStatusIcon(), "X");
    }

    @Test
    public void toDataTest() {
        Task test1 = new Task("test1",false);
        Task test2 = new Task("test2",true);
        assertEquals(test1.toData(), "0 | test1");
        assertEquals(test2.toData(), "1 | test2");
    }
}
