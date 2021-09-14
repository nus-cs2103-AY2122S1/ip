package agent.messages;

import agent.tasks.Task;

/**
 * Class is responsible for generating message indicating given task is marked as done.
 *
 * @author kevin9foong
 */
public class TaskDoneMessage extends Message {
    /**
     * Constructs an instance of <code>TaskDoneMessage</code> which contains a message
     * to inform user of which task has just been marked as done.
     *
     * @param task task which has been marked as done.
     */
    public TaskDoneMessage(Task task) {
        super(MessageConstants.MESSAGE_TASK_DONE_HEADER + "\n\t" + task.toString());
    }
}
