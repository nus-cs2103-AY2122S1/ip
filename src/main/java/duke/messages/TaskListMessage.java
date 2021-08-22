package duke.messages;

import duke.tasks.Task;

import java.util.List;

/**
 * Class is responsible for generating message to list all tasks
 * based on given task list.
 *
 * @author kevin9foong
 */
public class TaskListMessage extends Message {
    public TaskListMessage(List<Task> tasks) {
        super.setMessageText(generateListMessageText(tasks));
    }

    /**
     * Generates message from given list.
     *
     * @param tasks tasks to be included in the message.
     * @return user-friendly message including textual representations of all tasks
     * provided in the given list.
     */
    private String generateListMessageText(List<Task> tasks) {
        StringBuilder listMessageBuilder = new StringBuilder();
        listMessageBuilder.append(MessageConstants.TASK_LIST_HEADER);
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
