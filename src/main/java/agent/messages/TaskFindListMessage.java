package agent.messages;

import java.util.List;

import agent.tasks.Task;

/**
 * Class is responsible for forming message which displays tasks which descriptions contains
 * the search query <code>String</code>.
 *
 * @author kevin9foong
 */
public class TaskFindListMessage extends Message {

    /**
     * Constructs an instance of <code>TaskFindListMessage</code> which generates a message
     * as response of a find command and detailing all tasks provided as argument.
     *
     * @param tasks tasks to include in output message.
     */
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
