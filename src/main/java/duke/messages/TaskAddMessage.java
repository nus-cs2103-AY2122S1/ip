package duke.messages;

/**
 * Class is responsible for generating message to indicate the given task has been added.
 *
 * @author kevin9foong
 */
public class TaskAddMessage extends Message {
    public TaskAddMessage(String taskText, int numOfTasks) {
        super(MessageConstants.MESSAGE_TASK_ADD_HEADER
                + taskText
                + "\nNow you have "
                + numOfTasks
                + (numOfTasks == 1 ? " task " : " tasks ")
                + "in the list.");
    }
}
