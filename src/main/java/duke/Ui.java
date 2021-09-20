package duke;

import java.util.Scanner;

import duke.tasks.Task;

public class Ui {
    private Scanner scanner;

    Ui() {
        this.scanner = new Scanner(System.in); // Create a Scanner object
    }
    

    public String showWelcomeMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String result = "Hello from\n" + logo + "\n";
        result += "Hello! I'm Duke\n" + // Welcome Message
                "What can I do for you?";
        return result;
    }
    public String showListMessage() {
        return "Here are the tasks in your list:";
    }

    public void showHelpMessage() {
    }

    public String showAddMessage(Task task, Tasklist tasklist) {
        String result = "Got it. I've added this task:\n";
        result += " " + task + '\n';
        result += (String.format("Now you have %d tasks in the list", tasklist.getTasklistSize()));
        return result;
    }

    public String showLoadingError() {
        return "The file you've loaded does not work. Delete it to start a new";
    }

    public String showByeMessage() {
        return "Bye. Hope to see you again soon!";
    }
    public String showDeleteMessage(Task task, Tasklist tasklist) {
        String result = "Noted. I've removed this task:\n";
        result += " " + task + "\n";
        result += (String.format("Now you have %d tasks in the list", tasklist.getTasklistSize()));
        return result;
    }

    public String showDoneMessage(Task task, Tasklist tasklist) {
        String result = "Nice! I've marked this task as done\n";
        result += task;
        return result;
    }

    public String showFindMessage() {
        return "Here are the matching tasks in your list:";
    }
}
