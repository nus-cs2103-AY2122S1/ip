package lifeline.ui;

import java.util.Scanner;

import lifeline.task.Task;
import lifeline.task.TaskList;

public class Ui {
    private Scanner sc;

    public Ui() {
        this.sc = new Scanner(System.in);
    }

    public String readCommand() {
        return sc.nextLine().trim();
    }

    public String showTaskList(TaskList taskList) {
        StringBuilder stringBuilder = new StringBuilder();
        if (taskList.size() == 0) {
            stringBuilder.append("You have no remaining tasks.\n");
        } else {
            int uncompletedTask = 0;
            stringBuilder.append("Here " + (taskList.size() > 1 ? "are" : "is")
                    + " your " + (taskList.size() > 1 ? "tasks:" : "task:") + "\n");
            for (int i = 0; i < taskList.size(); i++) {
                Task currTask = taskList.get(i);
                stringBuilder.append((i + 1) + ". " + currTask + "\n");
                if (!currTask.isDone()) {
                    uncompletedTask++;
                }
            }
            stringBuilder.append("You have " + uncompletedTask + " uncompleted " + (uncompletedTask > 1 ? "tasks"
                    : "task") + ".\n");
        }
        return stringBuilder.toString();
    }

    public String showTask(Task task) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(task + "\n");
        return stringBuilder.toString();
    }

    public String showDeletedTask(Task task) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("I have removed the task:\n" + task + "\n");
        return stringBuilder.toString();
    }

    public String showAddedTask(Task task) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("I have added this task for you:\n" + task + "\n");
        return stringBuilder.toString();
    }

    public String showCompletedTask(Task task) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("You have completed the " + task.getClass().getSimpleName().toLowerCase() + ":\n"
                + task.getName() + "\n");
        return stringBuilder.toString();
    }

    public String showError(String errorMessage) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(errorMessage + "\n");
        return stringBuilder.toString();
    }

    public String greet(TaskList taskList) {
        String lifeline = " _      _____ ______ ______ _      _____ _   _ ______\n"
                + "| |    |_   _|  ____|  ____| |    |_   _| \\ | |  ____|\n"
                + "| |      | | | |__  | |__  | |      | | |  \\| | |__\n"
                + "| |      | | |  __| |  __| | |      | | | . ` |  __|\n"
                + "| |____ _| |_| |    | |____| |____ _| |_| |\\  | |____\n"
                + "|______|_____|_|    |______|______|_____|_| \\_|______|\n";
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Hello! I am\n" + lifeline);
        stringBuilder.append("What can I help you with today?\n");
        return stringBuilder.toString();
    }

    public String exit() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Goodbye! Thanks for chatting with me!\n");
        return stringBuilder.toString();
    }

    public String echo(String input) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("You have said \"" + input + "\"\n");
        return stringBuilder.toString();
    }

    public void printToConsole(String response) {
        System.out.println(response);
    }
}
