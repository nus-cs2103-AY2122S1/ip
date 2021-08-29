package duke.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskManager;

public class DeleteTaskCommandTest {
    @Test
    public void execute_noArguments_exceptionThrown() {
        try {
            new DeleteTaskCommand("")
                    .execute(new TaskManager(), new Storage("./data/tasks.txt"));
            fail();
        } catch (DukeException e) {
            assertEquals(
                    "Invalid use of the 'delete' command.\n\nTo delete a task, use 'delete <task-number>'.",
                    e.getMessage()
            );
        }
    }

    @Test
    public void execute_nonIntegerArgument_exceptionThrown() {
        try {
            new DeleteTaskCommand("blah")
                    .execute(new TaskManager(), new Storage("./data/tasks.txt"));
            fail();
        } catch (DukeException e) {
            assertEquals(
                    "Invalid task number.",
                    e.getMessage()
            );
        }
    }
}
