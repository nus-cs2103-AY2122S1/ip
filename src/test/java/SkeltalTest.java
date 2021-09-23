import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import skeltal.Parser;
import skeltal.SkeltalException;
import skeltal.Storage;
import skeltal.task.TaskList;
import skeltal.task.types.Deadline;
import skeltal.task.types.Event;
import skeltal.task.types.ToDo;

public class SkeltalTest {

    @Test
    public void parserTest() {
        TaskList.resetList();
        String reply = Parser.parse("todo quick");
        assertEquals("Got it. I've added this task\n"
                + "[T][ ] 1. quick\n"
                + "Now you have 1 tasks in the list.", reply);
    }

    @Test
    public void taskListTest() throws SkeltalException {
        TaskList.resetList();
        String reply = "";
        Event event = Event.of("event test /at 12112020");
        Deadline deadline = Deadline.of("deadline test /by 12112020");
        ToDo todo = ToDo.of("todo test");
        reply += TaskList.addTask(event) + "\n";
        reply += TaskList.addTask(deadline) + "\n";
        reply += TaskList.addTask(todo) + "\n";
        reply += TaskList.listReply();
        String expected = "Got it. I've added this task\n"
                + "[E][ ] 1. event test (at: 12 Nov 20)\n"
                + "Now you have 1 tasks in the list.\n"
                + "Got it. I've added this task\n"
                + "[D][ ] 2. deadline test (by: 12 Nov 20)\n"
                + "Now you have 2 tasks in the list.\n"
                + "Got it. I've added this task\n"
                + "[T][ ] 3. todo test\n"
                + "Now you have 3 tasks in the list.\n"
                + "Here are the tasks in your list:\n"
                + "[E][ ] 1. event test (at: 12 Nov 20)\n"
                + "[D][ ] 2. deadline test (by: 12 Nov 20)\n"
                + "[T][ ] 3. todo test";
        assertEquals(expected, reply);
    }

    @Test
    public void loaderTest() {
        String reply = Storage.loadFile(Storage.SKELTAL_PATH, Storage.wrappedStringToTask).getValue();
        assertEquals("Saved tasks have been loaded into the skeltal system!", reply);
    }

    @Test
    public void tasksTest() throws SkeltalException {
        String reply = "";
        TaskList.resetList();
        Event event = Event.of("event test /at 12112020");
        Deadline deadline = Deadline.of("deadline test /by 12112020");
        ToDo todo = ToDo.of("todo test");

        reply += TaskList.addTask(event) + "\n";
        reply += TaskList.addTask(deadline) + "\n";
        reply += TaskList.addTask(todo);
        String expected = "Got it. I've added this task\n"
                + "[E][ ] 1. event test (at: 12 Nov 20)\n"
                + "Now you have 1 tasks in the list.\n"
                + "Got it. I've added this task\n"
                + "[D][ ] 2. deadline test (by: 12 Nov 20)\n"
                + "Now you have 2 tasks in the list.\n"
                + "Got it. I've added this task\n"
                + "[T][ ] 3. todo test\n"
                + "Now you have 3 tasks in the list.";

        assertEquals(expected, reply);
    }
}
