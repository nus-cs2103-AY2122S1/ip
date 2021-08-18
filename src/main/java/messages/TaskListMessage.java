package messages;

import tasks.Task;

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

    private String generateListMessageText(List<Task> tasks) {
        StringBuilder listMessageText = new StringBuilder();
        for (int index = 0; index < tasks.size(); index++) {
            if (index != 0) {
                listMessageText.append("\n");
            }
            listMessageText.append(index + 1).append(".")
                    .append(tasks.get(index).toString());
        }
        return listMessageText.toString();
    }
}
