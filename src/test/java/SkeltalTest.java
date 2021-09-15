import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.BeforeEach;
import skeltal.*;
import org.junit.jupiter.api.Test;
import skeltal.task.types.Deadline;
import skeltal.task.types.Event;
import skeltal.task.TaskList;
import skeltal.task.types.ToDo;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SkeltalTest {

    private ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    public void parserTest() {
        Parser.parse("todo quick");
        assertEquals("---------------------------------------------\n" +
                "Got it. I've added this task\n" +
                "[T][ ] 1. quick\n" +
                "Now you have 1 tasks in the list.\n" +
                "wrote to skeltal.txt\n" +
                "---------------------------------------------", outputStreamCaptor.toString().trim());
    }

    @Test
    public void taskListTest() {
        try {
            Event event = new Event("event test /at 12pm");
            Deadline deadline = new Deadline("deadline test /by 12pm");
            ToDo todo = new ToDo("todo test");
            TaskList.addTask(event);
            TaskList.addTask(deadline);
            TaskList.addTask(todo);
            TaskList.listReply();
            String expected = "Got it. I've added this task\n" +
                    "[E][ ] 1. event test (at: 12pm)\n" +
                    "Now you have 1 tasks in the list.\n" +
                    "Got it. I've added this task\n" +
                    "[D][ ] 2. deadline test (by: 12pm)\n" +
                    "Now you have 2 tasks in the list.\n" +
                    "Got it. I've added this task\n" +
                    "[T][ ] 3. todo test\n" +
                    "Now you have 3 tasks in the list.\n" +
                    "[E][ ] 1. event test (at: 12pm)\n" +
                    "[D][ ] 2. deadline test (by: 12pm)\n" +
                    "[T][ ] 3. todo test";
            assertEquals(expected, outputStreamCaptor.toString().trim());
        } catch (SkeltalException e) {

        }
    }

    @Test
    public void loaderTest() {
        Storage.loadFile();
        assertEquals("Saved tasks have been loaded into the skeltal system!", outputStreamCaptor.toString().trim());
    }

    @Test
    public void tasksTest() {
        try {
            Event event = new Event("event test /at 12pm");
            Deadline deadline = new Deadline("deadline test /by 12pm");
            ToDo todo = new ToDo("todo test");
            TaskList.addTask(event);
            TaskList.addTask(deadline);
            TaskList.addTask(todo);
        } catch (SkeltalException e) {
        }
        String expected = "Got it. I've added this task\n" +
                "[E][ ] 1. event test (at: 12pm)\n" +
                "Now you have 1 tasks in the list.\n" +
                "Got it. I've added this task\n" +
                "[D][ ] 2. deadline test (by: 12pm)\n" +
                "Now you have 2 tasks in the list.\n" +
                "Got it. I've added this task\n" +
                "[T][ ] 3. todo test\n" +
                "Now you have 3 tasks in the list.";

        assertEquals(expected, outputStreamCaptor.toString().trim());
    }

}
