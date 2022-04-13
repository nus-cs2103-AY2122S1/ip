import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

import ailurus.Parser;
import ailurus.Storage;
import ailurus.task.Deadline;
import ailurus.task.Event;
import ailurus.task.Task;
import ailurus.task.TaskList;
import ailurus.task.Todo;


public class AilurusTest {
    @Test
    public void parseTest() {
        String command = "deadline";

        String deadlineCorrect = "deadline CS2103T ip week 2 /by 2021-08-19";
        String deadlineCorrect1 = "deadline test";
        String deadlineWrong = "deadlinetest";

        assertEquals(Parser.parse(deadlineCorrect), command);
        assertEquals(Parser.parseMessage(deadlineCorrect), "CS2103T ip week 2 /by 2021-08-19");
        assertEquals(Parser.parse(deadlineCorrect1), command);
        assertEquals(Parser.parseMessage(deadlineCorrect1), "test");
        assertNotEquals(Parser.parse(deadlineWrong), command);
    }

    @Test
    public void taskTest() {
        Task task1 = new Todo("Go grocery shopping");
        Task task2 = new Deadline("CS2103T ip week 2 /by 2021-08-19");
        Task task3 = new Event("CS2103T lecture /at 2021-08-27");

        String task1String = "[T][ ]Go grocery shopping";
        String task2String = "[D][ ]CS2103T ip week 2 (by: Aug 19 2021)";
        String task3String = "[E][ ]CS2103T lecture (at: Aug 27 2021)";

        assertEquals(task1.toString(), task1String);
        assertEquals(task2.toString(), task2String);
        assertEquals(task3.toString(), task3String);
    }

    @Test
    public void storageTest() {
        // loading of storage
        Storage storage = new Storage("./data/", "testData.txt");
        TaskList tasklist = new TaskList(storage.load());
        assertEquals(tasklist.length(), 0);

        // adding 2 tasks
        tasklist.addTask(new Todo("test1"));
        tasklist.addTask(new Todo("test2"));

        // reloading storage with 2 tasks
        storage.unload(tasklist);
        TaskList newTaskList = new TaskList(storage.load());
        assertEquals(newTaskList.length(), 2);

        // emptying storage with 0 tasks
        storage.unload(new TaskList());
        TaskList emptyTaskList = new TaskList(storage.load());
        assertEquals(emptyTaskList.length(), 0);
    }
}
