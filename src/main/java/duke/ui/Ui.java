package duke.ui;

import duke.task.Task;

import java.util.Scanner;

/**
 * <code>Ui</code> class manages user input and output
 * Ui object has one attribute reader which makes use of Scanner library to read user input
 * Also contains 4 methods showHelp(), showError(errorType), showMessage() and taskCreationSuccess()
 * These deal with Ui Output.
 */

public class Ui{

    Scanner reader;
    public Ui(){
        this.reader = new Scanner(System.in);
    }

    /**
     * Prompts user to give text input, then reads and returns input as a String.
     *
     * @param prompt a string which shows a prompt for the user to type in an input
     * @return User input string to be fed into Parser
     */

    public String readInput(String prompt) {
        System.out.println(prompt);
        return this.reader.nextLine();
    }

    public void showHelp() {
        String logo = "                                        _       \n"
                + "  _ __  _ _   _ __  ___ ___ ___ ___ ___| |__ ___\n"
                + " | '  \\| '_| | '  \\/ -_) -_|_-</ -_) -_) / /(_-<\n"
                + " |_|_|_|_|   |_|_|_\\___\\___/__/\\___\\___|_\\_\\/__/\n"
                + "\n";

        System.out.println(logo);
        System.out.println("I'm Mr Meeseeks look at me!");
        System.out.println("Type in \"todo\", \"deadline\" or \"event\" and I will keep track of a task!");
        System.out.println("Type \"list\" to show all tasks so far");
        System.out.println("Type \"done\" to mark a task as done");
        System.out.println("Type \"find\" to search for a task by keywords");
        System.out.println("Type \"bye\" to exit");
    }

    /**
     * Prints out specific error messages for specific errors caught by the bot
     *
     * @param errorType a string which indicates the type of error detected/caught
     */

    public void showError(String errorType) {
        switch (errorType) {
            case "loadFail":
                System.out.println("Oops! I couldn't load the task file.");
                System.out.println("Starting over with an empty task list.");
            case "invalidCommand":
                System.out.println("Oops! Sorry I don't recognise that command!");
                System.out.println("Type \"help\" for list of commands, "
                        + "check for illegal characters/trailing spaces");
                break;
            case "emptyInput":
                System.out.println("Oops! Please input something.");
                break;
            case "invalidDate":
                System.out.println("Oops! Please follow the format: yyyy-mm-dd hh:mm "
                        + "or I can't process the date!");
                break;
            case "noTasks":
                System.out.println("Oops! There are no tasks yet!");
                break;
            case "maxTasks":
                System.out.println("Oops! My task list is full, no new tasks can be added.");
                break;
            case "invalidIndex":
                System.out.println("Oops! Please enter a valid task number.");
                break;
            default:
                System.out.println("Oops! An unknown error occurred.");
        }

    }

    public void showMessage(String s) {
        System.out.println(s);
    }

    public void taskCreationSuccess(Task t) {
        System.out.println("Ok! I've created and added the task below");
        System.out.println(t);
    }

}