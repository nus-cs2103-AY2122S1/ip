package duke.ui;

import java.util.Scanner;

import duke.task.Task;

/**
 * Represents the user interface that the user uses to interact with the program.
 */

public class Ui {
    private Scanner reader;

    private String logo = "┏━━┓╋╋╋┏━━┓╋╋╋┏┓\n"
            + "┃┏┓┃╋╋╋┃┏┓┃╋╋┏┛┗┓\n"
            + "┃┗┛┗┳━┳┫┗┛┗┳━┻┓┏┛\n"
            + "┃┏━┓┃┏╋┫┏━┓┃┏┓┃┃\n"
            + "┃┗━┛┃┃┃┃┗━┛┃┗┛┃┗┓\n"
            + "┗━━━┻┛┗┻━━━┻━━┻━┛";

    /**
     * Creates a new instance of Ui with a scanner to take in user input.
     */
    public Ui() {
        this.reader = new Scanner(System.in);
    }

    /**
     * Greets the user.
     */
    public String sayHello() {
        return "Greetings from Bribot\nWhat can I do for you?\n";
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
    public String printLine() {
        return "____________________________________________________________";
    }

    /**
     * Prints out that the given tasks has been marked.
     * @param task the given task.
     */
    public String markTask(Task task) {
        // this.printLine();
        return task.toString();
        // this.printLine();
    }

    /**
     * Prints out the given task at with the index to show the tasks position in the list.
     * @param task given task
     * @param index index to show the position in the list.
     */
    public String printTask(Task task, int index) {
        return index + ". " + task.toString()+ "\n";
    }

    /**
     * Prints the start of the list feature.
     */
    public String printStartList() {
        return "Here are the tasks in your list:\n";
    }

    /**
     * Prints that the given command is not understood by the program.
     */
    public String printUnknownCommand() {
//        this.printLine();
        return "☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n";
//        this.printLine();
    }

    /**
     * Says good bye to the user.
     */
    public String sayGoodBye() {
//        this.printLine();
        return "Bye. Hope to see you soon!\n";
//        this.printLine();
    }

    /**
     * Prints out the given message.
     * @param message the given message.
     */
    public String printDukeException(String message) {
        return message;
    }

    /**
     * Prints that the task has been successfully added to the user.
     * @param task the given task to be printed.
     */
    public String printAddedTask(Task task) {
//        this.printLine();
        return "Done! added the following task:\n" + task;
//        this.printLine();
    }

    /**
     * Prints that the given task has been successfully deleted to the user.
     * @param task the given task to be deleted.
     */
    public String printDeletedTask(Task task) {
//        this.printLine();
        return "Done! deleted the following task:\n" + task;
//        this.printLine();
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
    public String showLoadingError() {
        return "ERROR LOADING FILE";
    }

    public String printMessage(String s) {
        return s;
    }
}
