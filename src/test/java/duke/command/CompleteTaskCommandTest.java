package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskManager;
import duke.ui.Ui;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class CompleteTaskCommandTest {
    @Test
    public void execute_noArguments_exceptionThrown() {
        try {
            new CompleteTaskCommand("").
                    execute(new TaskManager(), new Ui(), new Storage("./data/tasks.txt"));
            fail();
        } catch (DukeException e) {
            assertEquals(
                    "Invalid use of the 'done' command.\n\nTo mark a task as done, use 'done <task-number>'.",
                    e.getMessage()
            );
        }
    }

    @Test
    public void execute_nonIntegerArgument_exceptionThrown() {
        try {
            new CompleteTaskCommand("blah").
                    execute(new TaskManager(), new Ui(), new Storage("./data/tasks.txt"));
            fail();
        } catch (DukeException e) {
            assertEquals(
                    "Invalid task number.",
                    e.getMessage()
            );
        }
    }
}
