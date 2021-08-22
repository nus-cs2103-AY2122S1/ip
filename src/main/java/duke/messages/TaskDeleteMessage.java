package duke.messages;

/**
 * Class is responsible for generating a message when the given task has been deleted.
 *
 * @author kevin9foong
 */
public class TaskDeleteMessage extends Message {
    public TaskDeleteMessage(String taskDescription, int numOfTasks) {
        super(MessageConstants.TASK_DELETE_HEADER
                + taskDescription
                + "\nNow you have " + numOfTasks
                + (numOfTasks == 1 ? " task " : " tasks ")
                + "in the list.");
    }
}
