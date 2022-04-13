package agent.command;

import agent.exceptions.EmptyTodoBodyException;
import agent.exceptions.TaskFileIoException;
import agent.messages.TaskAddMessage;
import agent.tasks.Task;
import agent.tasks.TaskList;
import agent.tasks.ToDo;

/**
 * Represents user command to add a <code>ToDo</code> to persisted tasks.
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
     * @param taskList handles task operations including adding, deleting, marking as done and retrieval.
     * @return response message by chat bot for adding todo.
     * @throws TaskFileIoException    thrown when failure due to reading or writing to task save file occurs.
     * @throws EmptyTodoBodyException thrown when the data String representing the
     *                                <code>ToDo</code> is missing.
     */
    @Override
    public String execute(TaskList taskList)
            throws TaskFileIoException, EmptyTodoBodyException {
        Task addedToDo = taskList.addTask(new ToDo(super.getUserInputBody()));
        return new TaskAddMessage(addedToDo.toString(),
                taskList.getNumOfTasks()).toString();
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
