package duke.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import duke.exceptions.DuplicateTaskException;
import duke.tasks.Todo;
import duke.utils.TaskList;

public class AddTodoCommandTest {

    @Test
    public void testValidTodoCommand1() {
        String expected = "TO ADD: [T][ ] PLACEHOLDER SENTENCE";
        Command actual = new AddTodoCommand(new String[] {"PLACEHOLDER SENTENCE"});
        assertEquals(
                expected,
                actual.toString()
        );
    }

    @Test
    public void testValidTodoCommand2() {
        String expected = "TO ADD: [T][ ] PLACEHOLDER /at sometime";
        Command actual = new AddTodoCommand(new String[] {"PLACEHOLDER /at sometime"});
        assertEquals(
                expected,
                actual.toString()
        );
    }

    @Test
    public void testAddDuplicateTodo() throws DuplicateTaskException {
        TaskList taskList = new TaskList();
        taskList.addTask(new Todo("Placeholder"));

        AddTodoCommand addTodoCommand = new AddTodoCommand(new String[] {"Placeholder"});
        assertThrows(
                DuplicateTaskException.class,
                () -> addTodoCommand.execute(taskList, null, null)
        );
    }
}
