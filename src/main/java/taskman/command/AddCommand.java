package taskman.command;


import taskman.exception.DukeException;
import taskman.tasktypes.Task;
import taskman.util.Storage;
import taskman.util.TaskList;

/**
 * Command that adds task into tasklist
 */
public class AddCommand extends Command {

    private final String taskDetails;
    private final String addType;

    /**
     * Basic Constructor
     *
     * @param storage StorageTxt object to save
     * @param taskList Tasklist to add task to
     * @param taskDetails User input
     * @param addType
     */
    public AddCommand(Storage storage, TaskList taskList, String taskDetails, String addType){
        super(storage, taskList, false);
        this.taskDetails = taskDetails;
        this.addType = addType;
    }

    /**
     * Executes a set of instructions to add a task into list and storage
     *
     * @return String success message
     * @throws DukeException
     */
    public String exec() throws DukeException {
        switch (addType) {
        case "deadline":
            Task deadline = taskList.deadline(taskDetails);
            storage.saveAddedTask(deadline);
            return successfullyAddedTask(deadline);
            // returns success message after adding deadline
        case "todo":
            Task todo = taskList.todo(taskDetails);
            storage.saveAddedTask(todo);
            return successfullyAddedTask(todo);
            // returns success message after adding task
        case "event":
            Task event = taskList.event(taskDetails);
            storage.saveAddedTask(event);
            return successfullyAddedTask(event);
            // returns success message after adding event
        }
        return "";
    }

    /**
     * Returns a string success message
     *
     * @param task
     * @return
     */
    private String successfullyAddedTask(Task task) {
        String taskDetails = task.toString();
        int task_left = taskList.taskLeft();
        return successMessage(taskDetails, task_left);
    }

    /**
     * Formats a success message
     *
     * @param taskDetails
     * @param task_left
     * @return
     */
    private String successMessage(String taskDetails, int task_left) {
        return "Got it. I've added this task:\n " + taskDetails +  "\nNow you have "
                + task_left + " tasks in the list.";
    }


}
