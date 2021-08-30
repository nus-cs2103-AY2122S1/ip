package duke;

import duke.task.TaskList;
import duke.task.TodoTask;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DukeTest {

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    public void delete_when_taskList_empty() {
        Duke duke = new Duke("");
        TaskList taskList = new TaskList();
        Storage storage = new Storage("");
        Ui ui = new Ui();
        Parser parser = new Parser(duke, taskList, storage, ui);

        parser.parse("delete 1");
        assertEquals("No tasks in list!", outputStreamCaptor.toString().trim());
    }

    @Test
    public void addTodo_noInput() {
        Duke duke = new Duke("");
        TaskList taskList = new TaskList();
        Storage storage = new Storage("");
        Ui ui = new Ui();
        Parser parser = new Parser(duke, taskList, storage, ui);

        parser.parse("todo");
        assertEquals("Error: Description for To-do cannot be empty.\nexample:\ntodo buy groceries", outputStreamCaptor.toString().trim());

    }

    @Test
    public void markDone_outOfBounds() {
        Duke duke = new Duke("");
        TaskList taskList = new TaskList();
        Storage storage = new Storage("");
        Ui ui = new Ui();
        Parser parser = new Parser(duke, taskList, storage, ui);

        taskList.add(new TodoTask("test"));
        parser.parse("done 2").execute();
        assertEquals("Error: Invalid input, please enter a number from 1 to 1", outputStreamCaptor.toString().trim());
    }
}
