package jarvis.output;

import jarvis.message.OutputMessage;
import jarvis.task.TaskList;

public class Output {
    public static void showOutputMessage(OutputMessage message) {
        System.out.println(message.getMessage());
    }

    public static void showFormattedOutputMessage(OutputMessage message) {
        System.out.println(message.getFormattedMessage());
    }

    public static void showTaskList(TaskList taskList) {
        OutputMessage taskListMessage = new OutputMessage(taskList.toString());
        Output.showFormattedOutputMessage(taskListMessage);
    }
}
