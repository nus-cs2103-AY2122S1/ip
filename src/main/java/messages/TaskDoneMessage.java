package messages;

import tasks.Task;

/**
 * Class is responsible for generating message indicating task is marked as done.
 *
 * @author kevin9foong
 */
public class TaskDoneMessage extends Message{
    public TaskDoneMessage(Task task) {
        super(MessageConstants.TASK_DONE_HEADER + "\n\t" + task.toString());
    }
}
