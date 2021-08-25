import bobbybot.TaskList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class TaskListTest {
    TaskList tasks = new TaskList(new ArrayList<>());

    @Test
    public void todo_create_success() {
        tasks.createToDo("test description");
        assertEquals(tasks.getTasks().size(),1);
        assertEquals("[T][ ] test description", tasks.getTask(0).toString());
    }

    @Test
    public void deadline_create_success() {
        tasks.createDeadline("test description", "01-01-2021 12:00");
        assertEquals(tasks.getTasks().size(),1);
        assertEquals("[D][ ] test description (by: Jan 01 2021 12:00)", tasks.getTask(0).toString());
    }

    @Test
    public void deadline_createInvalid_nothingHappens() {
        try {
            tasks.createDeadline("test description", "01-01-20212 12:00");
        } catch (Exception e) {
            // nothing should be created from invalid by time format
            assertEquals(tasks.getTasks().size(),0);
        }
    }
    @Test
    public void event_create_success() {
        tasks.createEvent("test description", "this to that");
        assertEquals(tasks.getTasks().size(),1);
        assertEquals("[E][ ] test description (at: this to that)", tasks.getTask(0).toString());
    }

    @Test
    public void delete_deleteTask_success() {
        tasks.createEvent("test description", "this to that");
        tasks.deleteTask(1);
        assertEquals(0, tasks.getTasks().size());
    }

    @Test
    public void delete_deleteInvalidTask_success() {
        tasks.deleteTask(1);
        assertEquals("No exception thrown","No exception thrown");
    }

    @Test
    public void markAsDone_markTask_success() {
        tasks.createEvent("test description", "this to that");
        assertEquals("[E][ ] test description (at: this to that)", tasks.getTask(0).toString());
        tasks.markAsDone(1);
        assertEquals("[E][X] test description (at: this to that)", tasks.getTask(0).toString());
    }
    // testing print statements
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }
    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }
    @Test
    public void TaskList_printList_printSuccess() {
        tasks.createEvent("test description", "this to that");
        tasks.createDeadline("test description", "01-01-2021 12:00");
        tasks.createToDo("test description");
        tasks.printList();
        assertEquals("Got it. I've added this task:\n" +
                "  [E][ ] test description (at: this to that)\n" +
                "Now you have 1 tasks in the list.\n" +
                "Got it. I've added this task:\n" +
                "  [D][ ] test description (by: Jan 01 2021 12:00)\n" +
                "Now you have 2 tasks in the list.\n" +
                "Got it. I've added this task:\n" +
                "  [T][ ] test description\n" +
                "Now you have 3 tasks in the list.\n" +
                "Here are the tasks in your list:\n" +
                "1. [E][ ] test description (at: this to that)\n" +
                "2. [D][ ] test description (by: Jan 01 2021 12:00)\n" +
                "3. [T][ ] test description", outputStreamCaptor.toString()
                .trim());

    }
}
