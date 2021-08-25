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

    public void printList(TaskList lst) {
        boolean isEmptyList = false;
        int counter = 1;
        String output = "   Here are the tasks in your list: \n";

        while (!isEmptyList) {
            if (lst.getListOfTasks().isEmpty()) {
                output += "   Current List is empty...";
                isEmptyList = true;
            } else if (counter - lst.getListOfTasks().size() == 1) { // when there are no more tasks to iterate
                output = output.replaceAll("[\n\r]$", ""); // remove last newline
                isEmptyList = true;
            } else { // adds current task to the list based on counter index
                String lineAdded = String.format("   %d.%s \n", counter, lst.getListOfTasks().get(counter - 1));
                output += lineAdded;
                counter++;
            }
        }
        System.out.println(output);
    }

    public String printListForSave(TaskList lst) {
        String outputText = "";

        for (Task t: lst.getListOfTasks()) {
            if (t instanceof Todo) {
                outputText += "T | ";
                if (t.getStatusIcon().equals("X")) {
                    outputText += "X | " + t.getDescription() + "\n";
                } else {
                    outputText += "0 | " + t.getDescription() + "\n";
                }
            } else if (t instanceof Deadline) {
                Deadline d = (Deadline) t;
                outputText += "D | ";
                if (d.getStatusIcon().equals("X")) {
                    outputText += "X | " + d.getDescription() + " | " + d.getBy() + "\n";
                } else {
                    outputText += "0 | " + d.getDescription() + " | " + d.getBy() + "\n";
                }
            } else if (t instanceof Event) {
                Event e = (Event) t;
                outputText += "E | ";
                if (e.getStatusIcon().equals("X")) {
                    outputText += "X | " + e.getDescription() + " | " + e.getAt() + "\n";
                } else {
                    outputText += "0 | " + e.getDescription() + " | " + e.getAt() + "\n";
                }
            }
        }
        return outputText;
    }

    public String readCommand() {
        return sc.nextLine();
    }

    public String printEmptyDescription(String taskType) {
        return String.format("   OOPS!!! The description of a %s cannot be empty.", taskType);
    }
}
