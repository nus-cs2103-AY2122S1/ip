package duke.ui;

import duke.task.Task;

import java.util.Scanner;

/**
 * Represents the user interface that the user uses to interact with the program.
 */

public class Ui {
    private Scanner reader;

    String logo = "┏━━┓╋╋╋┏━━┓╋╋╋┏┓\n" +
            "┃┏┓┃╋╋╋┃┏┓┃╋╋┏┛┗┓\n" +
            "┃┗┛┗┳━┳┫┗┛┗┳━┻┓┏┛\n" +
            "┃┏━┓┃┏╋┫┏━┓┃┏┓┃┃\n" +
            "┃┗━┛┃┃┃┃┗━┛┃┗┛┃┗┓\n" +
            "┗━━━┻┛┗┻━━━┻━━┻━┛";

    /**
     * Creates a new instance of Ui with a scanner to take in user input.
     */
    public Ui() {
        this.reader = new Scanner(System.in);
    }

    /**
     * Greets the user.
     */
    public void sayHello() {
        System.out.println("Greetings from\n" + logo);
        this.printLine();
        System.out.println("What can I do for you?");
        this.printLine();
    }

    /**
     * Takes in the user input and returns it as a string.
     * @return the user input.
     */
    public String readCommand() {
        return reader.nextLine();
    }

    /**
     * prints out a separator line
     */
    public void printLine() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Prints out that the given tasks has been marked.
     * @param task the given task.
     */
    public void markTask(Task task) {
        this.printLine();
        System.out.println(task);
        this.printLine();
    }

    /**
     * Prints out the given task at with the index to show the tasks position in the list.
     * @param task given task
     * @param index index to show the position in the list.
     */
    public void printTask(Task task, int index) {
        System.out.println(index + ". " + task);
    }

    /**
     * Prints the start of the list feature.
     */
    public void printStartList() {
        this.printLine();
        System.out.println("Here are the tasks in your list:");
    }

    /**
     * Prints that the given command is not understood by the program.
     */
    public void printUnknownCommand() {
        this.printLine();
        System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        this.printLine();
    }

    /**
     * Says good bye to the user.
     */
    public void sayGoodBye() {
        this.printLine();
        System.out.println("Bye. Hope to see you soon!");
        this.printLine();
    }

    /**
     * Prints out the given message.
     * @param message the given message.
     */
    public void printDukeException(String message) {
        this.printLine();
        System.out.println(message);
        this.printLine();
    }

    /**
     * Prints that the task has been successfully added to the user.
     * @param task the given task to be printed.
     */
    public void printAddedTask(Task task) {
        this.printLine();
        System.out.println("Done! added the following task:");
        System.out.println(task);
        this.printLine();
    }

    /**
     * Prints that the given task has been successfully deleted to the user.
     * @param task the given task to be deleted.
     */
    public void printDeletedTask(Task task) {
        this.printLine();
        System.out.println("Done! deleted the following task:");
        System.out.println(task);
        this.printLine();
    }

    /**
     * Closes the scanner.
     */
    public void closeScanner() {
        this.reader.close();
    }

    /**
     * Show that there was an error reading from the text file.
     */
    public void showLoadingError() {
        System.out.println("ERROR LOADING FILE");
    }

    public void printMessage(String s) {
        System.out.println(s);
    }
}
