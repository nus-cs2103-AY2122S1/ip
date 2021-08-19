import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;
import duke.command.Command;
import duke.command.AddCommand;
import duke.Storage;
import duke.TaskList;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddCommandTest {
    //store is irrelevant, tl code is simple enough that i did not stub it.
    Storage store = new Storage("");
    TaskList tl = new TaskList();
    UiStub ui = new UiStub();


    @Test
    void testAddTask_addOneTodo_listWithOneTodo() {
        Command c = new AddCommand("todo", "a simple todo task");
        c.execute(tl, ui, store);
        Task expected = new Todo("a simple todo task");
        assertEquals(expected.getLabel(),tl.get(0).getLabel()); //same label
        assertEquals(expected.getType(), tl.get(0).getType()); //same type
        assertEquals(1, tl.size()); //exactly one task added
    }

    @Test
    void testAddTask_addOneDeadline_listWithOneDeadline() {
        LocalDate date = LocalDate.parse("2020-02-20");
        Command c = new AddCommand("deadline", "a simple deadline task", date);
        c.execute(tl, ui, store);
        Task expected = new Deadline("a simple deadline task", date);
        assertEquals(expected.getLabel(), tl.get(0).getLabel());
        assertEquals(expected.getType(), tl.get(0).getType());
        assertEquals(1, tl.size());
    }

    @Test
    void testAddTask_addOneEvent_listWithOneEvent() {
        LocalDate date = LocalDate.parse("2021-01-10");
        Command c = new AddCommand("event", "a simple event task", date);
        c.execute(tl, ui, store);
        Task expected = new Event("a simple event task", date);
        assertEquals(expected.getLabel(), tl.get(0).getLabel());
        assertEquals(expected.getType(), tl.get(0).getType());
        assertEquals(1, tl.size());
    }
}