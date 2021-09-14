package agent.messages;

import java.util.List;

import agent.tasks.Task;

/**
 * Class is responsible for generating message to list all tasks
 * based on given task list.
 *
 * @author kevin9foong
 */
public class TaskListMessage extends Message {

    /**
     * Constructs an instance of <code>TaskListMessage</code> which generates a message as
     * response to a list command and lists all tasks provided as argument.
     *
     * @param tasks tasks to include in output message.
     */
    public TaskListMessage(List<? extends Task> tasks) {
        super.setMessageText(MessageConstants.MESSAGE_TASK_LIST_HEADER
                + generateListMessageText(tasks));
    }

    /**
     * Generates message from given list.
     *
     * @param tasks tasks to be included in the message.
     * @return user-friendly message including textual representations of all tasks
     * provided in the given list.
     */
    private String generateListMessageText(List<? extends Task> tasks) {
        StringBuilder listMessageBuilder = new StringBuilder();
        for (int index = 0; index < tasks.size(); index++) {
            listMessageBuilder
                    .append("\n")
                    .append(index + 1)
                    .append(".")
                    .append(tasks.get(index).toString());
        }
        return listMessageBuilder.toString();
    }
}
