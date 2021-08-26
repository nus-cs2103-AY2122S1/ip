package duke;

import duke.tasks.Task;

import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    private final String line;
    private final Scanner sc;

    Ui() {
        this.line = "   ----------------------------------------------------------------------------------------------";
        sc = new Scanner(System.in);
    }

    public void showWelcomeMessage() {
        String openingMessage = line
                + "\n   Hello! This is Duke :)"
                + "\n   To use my AUTOSAVE feature, please type 'bye' when you're done!"
                + "\n   Otherwise, I am unable to save your tasks for future reference :("
                + "\n   Now, what can I do for you? \n"
                + line;
        System.out.println(openingMessage);
    }

    public void showLoadingError() {
        System.out.println("   LOADING ERROR: Unable to load previously saved data...");
    }

    public void showError(String errorMessage) {
        if (errorMessage.equals("") || errorMessage == null) {
            System.out.println("   Unknown error was not handled by DukeException");
        } else {
            System.out.println(errorMessage);
        }
    }

    public void showByeMessage() {
        sc.close();
        String goodbyeMessage = "   Bye! Hope to see you again soon!";
        System.out.println(goodbyeMessage);
    }

    public void showLine() {
        System.out.println(this.line);
    }

    public void showAddTaskMessage(String taskDescription, int currentListSize) {
        String output = String.format("   Got it. I've added this task: \n      %s\n   Now you have %d tasks in the list.",
                taskDescription, currentListSize);
        System.out.println(output);
    }

    public void showDeletedTask(String taskDescription, int currentListSize) {
        String output = String.format("   Noted. I've deleted this task: \n      %s\n   Now you have %d tasks in the list.",
                taskDescription, currentListSize);
        System.out.println(output);
    }

    public void showCompletedTask(String taskInfo) {
        String output = String.format("   Nice! I've marked this task as done: \n      %s", taskInfo);
        System.out.println(output);
    }

    public void showInvalidCommand() {
        System.out.println("   INVALID INPUT: Start the sentence with either 'todo'/'deadline'/'event'/'list'/'done'/'delete'/'bye'");
    }

    public String processList(ArrayList<Task> listOfTasks) {
        String output = "";
        boolean isEmptyList = false;
        int counter = 1;

        while (!isEmptyList) {
            if (counter - listOfTasks.size() == 1) {
                output = output.replaceAll("[\n\r]$", ""); // remove last newline
                isEmptyList = true;
            } else {
                String lineAdded = String.format("   %d.%s \n", counter, listOfTasks.get(counter - 1));
                output += lineAdded;
                counter++;
            }
        }
        return output;
    }

    public void printList(TaskList lst) {
        if (lst.getListOfTasks().size() == 0) {
            System.out.println("   Current List is empty...");
        } else {
            String output = "   Here are the tasks in your list: \n";
            output += processList(lst.getListOfTasks());
            System.out.println(output);
        }
    }

    public void printFoundTasks(ArrayList<Task> listOfTasks, String keyWord) {
        if (listOfTasks.size() == 0) {
            System.out.println(String.format("  No matching tasks containing '%s' could be found :(", keyWord));
        } else {
            String output = "   Here are the matching tasks in your list: \n";
            output += processList(listOfTasks);
            System.out.println(output);
        }
    }

    public String readCommand() {
        return sc.nextLine();
    }

    public String printEmptyDescription(String taskType) {
        return String.format("   OOPS!!! The description of a %s cannot be empty.", taskType);
    }
}
