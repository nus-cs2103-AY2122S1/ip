package jarvis.output;

import jarvis.exception.JarvisException;
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

    public static void showError(JarvisException e) {
        OutputMessage errorMessage = new OutputMessage(e.getMessage());
        Output.showFormattedOutputMessage(errorMessage);
    }
}
