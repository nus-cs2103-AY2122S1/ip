import commands.AddDeadlineCommand;
import commands.AddEventCommand;
import commands.AddToDoCommand;
import commands.DeleteCommand;
import exceptions.DukeException;
import org.testng.annotations.Test;
import tasks.TaskList;
import tasks.ToDoTask;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InputParserTest {
    private final InputParser parser = new InputParser();
    private final Ui ui = new Ui();

    @Test
    public void testDelete() {
        TaskList t1 = new TaskList();
        TaskList t2 = new TaskList();
        t1.addTask(new ToDoTask("todo do CS2030 quiz"));
        t2.addTask(new ToDoTask("todo do CS2030 quiz"));
        try {
            String expected = (new DeleteCommand("delete 1")).execute(t1);
            String actual = parser.getCommand("delete 1").execute(t2);
            assertEquals(expected, actual);
        } catch (DukeException e) {
            ui.print(e.getMessage());
        }
    }

    @Test
    public void testMarkDone() {
        TaskList t1 = new TaskList();
        TaskList t2 = new TaskList();
        t1.addTask(new ToDoTask("todo do CS2030 quiz"));
        t2.addTask(new ToDoTask("todo do CS2030 quiz"));
        try {
            String expected = (new DeleteCommand("done 1")).execute(t1);
            String actual = parser.getCommand("done 1").execute(t2);
            assertEquals(expected, actual);
        } catch (DukeException e) {
            ui.print(e.getMessage());
        }
    }

    @Test
    public void testAddTodo() {
        TaskList t1 = new TaskList();
        TaskList t2 = new TaskList();
        try {
            String expected = (new AddToDoCommand("todo do CS2030 quiz")).execute(t1);
            String actual = parser.getCommand("todo do CS2030 quiz").execute(t2);
            assertEquals(expected, actual);
        } catch (DukeException e) {
            ui.print(e.getMessage());
        }
    }

    @Test
    public void testAddEvent() {
        TaskList t1 = new TaskList();
        TaskList t2 = new TaskList();
        try {
            String expected = (new AddEventCommand("event do CS2030 quiz /at 27-08-2021 18:00"))
                    .execute(t1);
            String actual = parser.getCommand("event do CS2030 quiz /at 27-08-2021 18:00")
                    .execute(t2);
            assertEquals(expected, actual);
        } catch (DukeException e) {
            ui.print(e.getMessage());
        }
    }

    @Test
    public void testAddDeadline() {
        TaskList t1 = new TaskList();
        TaskList t2 = new TaskList();
        try {
            String expected = (new AddDeadlineCommand("deadline do CS2030 quiz /by 27-08-2021 18:00"))
                    .execute(t1);
            String actual = parser.getCommand("deadline do CS2030 quiz /by 27-08-2021 18:00")
                    .execute(t2);
            assertEquals(expected, actual);
        } catch (DukeException e) {
            ui.print(e.getMessage());
        }
    }
}
