package messages;

/**
 * Class is responsible for generating message to indicate task has been added.
 *
 * @author kevin9foong
 */
public class TaskAddMessage extends Message {
    public TaskAddMessage(String taskText, int numOfTasks) {
        super(MessageConstants.TASK_ADD_HEADER
                + taskText
                + "\nNow you have "
                + numOfTasks
                + (numOfTasks == 1 ? " task " : " tasks ")
                + "in the list.");
    }
}
