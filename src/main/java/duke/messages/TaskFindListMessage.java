package duke.messages;

import duke.tasks.Task;

import java.util.List;

public class TaskFindListMessage extends Message {
    public TaskFindListMessage(List<Task> tasks) {
        super.setMessageText(MessageConstants.MESSAGE_FIND_TASK_LIST
            + generateListMessageText(tasks));
    }

    private String generateListMessageText(List<Task> tasks) {
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
