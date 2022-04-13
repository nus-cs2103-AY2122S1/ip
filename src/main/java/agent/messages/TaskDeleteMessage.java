package agent.messages;

/**
 * Class is responsible for generating a message when the given task has been deleted.
 *
 * @author kevin9foong
 */
public class TaskDeleteMessage extends Message {
    /**
     * Constructs an instance of <code>TaskDeleteMessage</code> which contains a message
     * to inform user of which task has just been deleted.
     *
     * @param taskDescription <code>String</code> representation of deleted task.
     * @param numOfTasks      remaining total number of tasks.
     */
    public TaskDeleteMessage(String taskDescription, int numOfTasks) {
        super(MessageConstants.MESSAGE_TASK_DELETE_HEADER
                + taskDescription
                + "\nNow you have " + numOfTasks
                + (numOfTasks == 1 ? " task " : " tasks ")
                + "in the list.");
    }
}
