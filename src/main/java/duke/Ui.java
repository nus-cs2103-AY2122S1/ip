package duke;

import java.util.Scanner;

public class Ui {
    private Scanner in;

    public Ui() {
        this.in = new Scanner(System.in);
    }

    public String readNextLine() throws DukeException {
        String fullCommand = in.nextLine();
        return fullCommand;
    }

    public void showAddMessage(String taskString, TaskList taskList) {
        System.out.println("Got it. I've added this task:\n"
                + "  " + taskString + "\n"
                + "Now you have " + taskList.getSize()
                + " task" + (taskList.getSize() > 1 ? "s" : "") + " in the list.");
    }

    public void showDeleteMessage(String taskString, TaskList taskList) {
        System.out.println("Noted. I've removed this task:\n"
                + "  "
                + taskString + "\n"
                + "Now you have " + taskList.getSize()
                + " task" + (taskList.getSize() > 1 ? "s" : "") + " in the list.");
    }

    public void showDoneMessage(String taskString) {
        System.out.println("Nice! I've marked this task as done:\n"
                + "  "
                + taskString);
    }

    public void showListMessage(TaskList taskList) {
        System.out.println("Here are the tasks in your list:");
        System.out.println(taskList.toString());
    }

    public void showFindMessage(String foundTasks) {
        System.out.println("Here are the matching tasks in your list:");
        System.out.println(foundTasks);
    }

    public void showError(String errorMessage) {
        System.out.println(errorMessage);
    }

    public void showWelcomeMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("Hello! I'm Nukem\n"
                + "What can I do for you?");
    }

    public void showEndMessage() {
        System.out.println("Bye. Hope to see you again soon!");
    }
}
