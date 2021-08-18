package messages;

import tasks.Task;

import java.util.List;

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
            listMessageText.append(index + 1).append(". ")
                    .append(tasks.get(index).toString());
        }
        return listMessageText.toString();
    }
}
