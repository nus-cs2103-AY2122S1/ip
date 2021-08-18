package messages;

/**
 * Class is responsible for generating message to indicate task has been added.
 *
 * @author kevin9foong
 */
public class TaskAddMessage extends Message {
    public TaskAddMessage(String taskText) {
        super(MessageConstants.TASK_ADD_HEADER + taskText);
    }
}
