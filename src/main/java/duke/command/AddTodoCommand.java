package duke.command;

import duke.exceptions.EmptyTodoBodyException;
import duke.exceptions.TaskFileIOException;
import duke.io.UserOutputHandler;
import duke.messages.TaskAddMessage;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.tasks.ToDo;

/**
 * Represents user command to add a <code>ToDo</code> to persisted Tasks.
 *
 * @author kevin9foong
 */
public class AddTodoCommand extends Command {


    /**
     * Constructs instance of <code>AddTodoCommand</code> which adds a <code>ToDo</code> based
     * on data provided within the given <code>userInputBody</code> <code>String</code>.
     *
     * @param userInputBody <code>String</code> containing description of <code>ToDo</code>.
     */
    public AddTodoCommand(String userInputBody) {
        super(userInputBody);
    }

    /**
     * Adds an <code>ToDo</code> to the <code>TaskList</code> and writes to user <code>ToDoAddMessage</code>.
     *
     * @param userOutputHandler handles outputting messages to the output destination.
     * @param taskList          handles task operations including adding, deleting, marking as done and retrieval.
     * @throws TaskFileIOException    thrown when failure due to reading or writing to task save file occurs.
     * @throws EmptyTodoBodyException thrown when the data String representing the
     *                                <code>ToDo</code> is missing.
     */
    @Override
    public void execute(UserOutputHandler userOutputHandler, TaskList taskList)
            throws TaskFileIOException, EmptyTodoBodyException {
        Task addedToDo = taskList.addTask(new ToDo(super.getUserInputBody()));
        userOutputHandler.writeMessage(new TaskAddMessage(addedToDo.toString(),
                taskList.getNumOfTasks()));
    }

    /**
     * Returns false to indicate program should not terminate after command is executed.
     *
     * @return false to indicate program should not terminate after command is executed.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
