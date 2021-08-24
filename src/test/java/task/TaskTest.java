package task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {
    @Test
    public void todoTest(){
        Task test = Task.createTask("todo", "Test Task Name");
        assertEquals("[T][ ] Test Task Name", test.toString());
        test.markDone();
        assertEquals("[T][X] Test Task Name", test.toString());
        String stored = test.saveTask();
        assertEquals("todo|Test Task Name|1", stored);
        Task retrieved = Task.getTask(stored);
        assertEquals(test.toString(), retrieved.toString());
    }

    @Test
    public void deadlineTest(){
        Task test = Task.createTask("deadline", "Test Task Name /by 2021-01-01");
        assertEquals("[D][ ] Test Task Name (by: Jan 1 2021)", test.toString());
        test.markDone();
        assertEquals("[D][X] Test Task Name (by: Jan 1 2021)", test.toString());
        String stored = test.saveTask();
        assertEquals("deadline|Test Task Name/by2021-01-01|1", stored);
        Task retrieved = Task.getTask(stored);
        assertEquals(test.toString(), retrieved.toString());
    }

    @Test
    public void eventTest(){
        Task test = Task.createTask("event", "Test Task Name /at 2021-01-01");
        assertEquals("[E][ ] Test Task Name (at: Jan 1 2021)", test.toString());
        test.markDone();
        assertEquals("[E][X] Test Task Name (at: Jan 1 2021)", test.toString());
        String stored = test.saveTask();
        assertEquals("event|Test Task Name/at2021-01-01|1", test.saveTask());
        Task retrieved = Task.getTask(stored);
        assertEquals(test.toString(), retrieved.toString());
    }
}
