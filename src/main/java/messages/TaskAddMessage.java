package messages;

public class TaskAddMessage extends Message {
    public TaskAddMessage(String taskText) {
        super(MessageConstants.TASK_ADD_HEADER + taskText);
    }
}
