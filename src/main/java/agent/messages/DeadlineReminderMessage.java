package agent.messages;

import java.util.List;

import agent.tasks.Task;

/**
 * Class is responsible for generating the message for the reminder command.
 *
 * @author kevin9foong
 */
public class DeadlineReminderMessage extends Message {
    /**
     * Constructs an instance of <code>DeadlineReminderMessage</code> which generates a message
     * which displays the list of deadlines in order of list provided.
     *
     * @param tasks deadlines to include in output message.
     */
    public DeadlineReminderMessage(List<? extends Task> tasks, String timeScope) {
        super.setMessageText(MessageConstants.MESSAGE_DEADLINE_REMINDER_HEADER + " (" + timeScope + "): "
                + generateDeadlineReminderText(tasks));
    }

    /**
     * Generates message from given list.
     *
     * @param tasks deadlines to be included in the message.
     * @return user-friendly message including textual representations of all tasks
     * provided in the given list.
     */
    private String generateDeadlineReminderText(List<? extends Task> tasks) {
        StringBuilder listMessageBuilder = new StringBuilder();
        for (Task task : tasks) {
            listMessageBuilder
                    .append("\n")
                    .append(task.toString());
        }
        return listMessageBuilder.toString();
    }
}
