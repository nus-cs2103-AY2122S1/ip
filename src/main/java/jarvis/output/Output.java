package jarvis.output;

import jarvis.exception.JarvisException;
import jarvis.message.ExitMessage;
import jarvis.message.GreetingMessage;
import jarvis.message.OutputMessage;
import jarvis.task.Task;
import jarvis.task.TaskList;

public class Output {
    private static void showOutputMessage(OutputMessage message) {
        System.out.println(message.getMessage());
    }

    private static void showFormattedOutputMessage(OutputMessage message) {
        System.out.println(message.getFormattedMessage());
    }

    public static void showGreetingMessage() {
        OutputMessage greetingMessage = new GreetingMessage();
        Output.showFormattedOutputMessage(greetingMessage);
    }

    public static void showExitMessage() {
        OutputMessage exitMessage = new ExitMessage();
        Output.showFormattedOutputMessage(exitMessage);
    }

    public static void showTaskList(TaskList taskList) {
        OutputMessage taskListMessage = new OutputMessage(String.format(
                "%s\n%s",
                "Here are the tasks in your list:",
                taskList.toString()
        ));
        Output.showFormattedOutputMessage(taskListMessage);
    }

    public static void showError(JarvisException e) {
        OutputMessage errorMessage = new OutputMessage(e.getMessage());
        Output.showFormattedOutputMessage(errorMessage);
    }

    public static void showTaskAddedMessage(Task task, TaskList taskList) {
        OutputMessage taskAddedMessage = new OutputMessage(String.format(
                "Alright! I have added this to the Stark Industries Database:\n\t%s\n%s",
                task.toString(),
                taskList.taskListSummary()
        ));
        Output.showFormattedOutputMessage(taskAddedMessage);
    }

    public static void showTaskDoneMessage(Task task) {
        OutputMessage taskDoneMessage = new OutputMessage(String.format(
                "Nice! I've marked this task as done:\n\t%s",
                task.toString()
        ));
        Output.showFormattedOutputMessage(taskDoneMessage);
    }
}
