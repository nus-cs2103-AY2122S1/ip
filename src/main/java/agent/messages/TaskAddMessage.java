package agent.messages;

/**
 * Class is responsible for generating message to indicate the given task has been added.
 *
 * @author kevin9foong
 */
public class TaskAddMessage extends Message {

    /**
     * Constructs an instance of <code>TaskAddMessage</code> which contains a message
     * to inform user that a <code>Task</code> has been added successfully.
     *
     * @param taskText   <code>String</code> representation of added task.
     * @param numOfTasks current total number of tasks.
     */
    public TaskAddMessage(String taskText, int numOfTasks) {
        super(MessageConstants.MESSAGE_TASK_ADD_HEADER
                + taskText
                + "\nNow you have "
                + numOfTasks
                + (numOfTasks == 1 ? " task " : " tasks ")
                + "in the list.");
    }
}
