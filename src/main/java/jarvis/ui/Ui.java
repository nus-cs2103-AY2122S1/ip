package jarvis.ui;

import jarvis.exception.JarvisException;
import jarvis.message.ExitMessage;
import jarvis.message.GreetingMessage;
import jarvis.message.OutputMessage;
import jarvis.task.Task;
import jarvis.task.TaskList;

import java.util.Scanner;

public class Ui {
    Scanner sc = new Scanner(System.in);

    private void showOutputMessage(OutputMessage message) {
        message.print();
    }

    private void showFormattedOutputMessage(OutputMessage message) {
        message.print();
    }

    public void showGreetingMessage() {
        OutputMessage greetingMessage = new GreetingMessage();
        greetingMessage.print();
    }

    public void showExitMessage() {
        OutputMessage exitMessage = new ExitMessage();
        exitMessage.print();
    }

    public void showTaskList(TaskList taskList) {
        OutputMessage taskListMessage = new OutputMessage(String.format(
                "%s\n%s",
                "Here are the tasks in your list:",
                taskList.toString()
        ));
        taskListMessage.print();
    }

    /**
     * Shows the matching task list message to the user
     *
     * @param taskList The task list containing the tasks
     */
    public void showMatchingTaskList(TaskList taskList) {
        OutputMessage taskListMessage = new OutputMessage(String.format(
                "%s\n%s",
                "Stark Industries Database contains these matching tasks:",
                taskList.toString()
        ));
        taskListMessage.print();
    }

    public void showError(JarvisException e) {
        OutputMessage errorMessage = new OutputMessage(e.getMessage());
        errorMessage.print();
    }

    public void showTaskAddedMessage(Task task, TaskList taskList) {
        OutputMessage taskAddedMessage = new OutputMessage(String.format(
                "Alright! I have added this to the Stark Industries Database:\n\t%s\n%s",
                task.toString(),
                taskList.taskListSummary()
        ));
        taskAddedMessage.print();
    }

    public void showTaskDoneMessage(Task task) {
        OutputMessage taskDoneMessage = new OutputMessage(String.format(
                "Nice! I've marked this task as done:\n\t%s",
                task.toString()
        ));
        taskDoneMessage.print();
    }

    public void showTaskDeletedMessage(Task task, TaskList taskList) {
        OutputMessage taskDeletedMessage = new OutputMessage(String.format(
                "Initiated Delete protocol. Delete confirmed for:\n\t%s\n%s",
                task.toString(),
                taskList.taskListSummary()
        ));
        taskDeletedMessage.print();
    }

    public String readInput() {
        return this.sc.nextLine();
    }

    public void closeScanner() {
        this.sc.close();
    }
}
