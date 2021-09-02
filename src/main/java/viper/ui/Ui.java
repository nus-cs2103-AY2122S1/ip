package viper.ui;

import java.util.Scanner;

import viper.tasks.Task;
import viper.tasks.TaskList;

/**
 * deals with interactions with the user.
 */
public class Ui {
    private static final String INDENT = "       ";
    private static final String DIVIDER = "\n ---------------------------------------------------------- \n";

    /**
     * Reads line of command.
     *
     * @return String of command.
     */
    public String readCommand() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    /**
     * Prints message with divider at the top and bottom.
     * With an indent before every line.
     *
     * @param strings Strings to be displayed to user.
     */
    public void showMessage(String[] strings) {
        System.out.println(DIVIDER);

        for (String string : strings) {
            System.out.println(INDENT + string);
        }
        System.out.println(DIVIDER);
    }

    /**
     * Prints message with an indent before the message.
     *
     * @param str String to be displayed to user.
     */
    public void printLine(String str) {
        System.out.println(INDENT + str);
    }

    /**
     * Prints divider.
     */
    public void printDiv() {
        System.out.println(DIVIDER);
    }

    /**
     * Prints welcome message, to be shown when Duke starts up at the beginning.
     */
    public void showWelcome() {
        String[] msg = {"Hello! I'm Viper :)", "I love to help!", "How can I help you today?"};
        showMessage(msg);
    }

    /**
     * Prints bye messsage, to be shown when user terminates Duke with "Bye" command.
     */
    public void showBye() {
        String[] msg = {"It was soooo fun talking to you!", "Bye! See you again~",
                "I will be seeing you again... right?"};
        showMessage(msg);
    }

    /**
     * Prints loading error message, to be shown during start of Duke.
     */
    public void showLoadingError() {
        String[] msg = {"Oh no, there was a problem loading this file...", "Wanna try another file instead?"};
        showMessage(msg);
    }

    /**
     * Prints invalid type error, to be shown when Type command is not included in Instructions enum.
     */
    public void showInvalidTypeError() {
        String[] msg = {"Oh no, I do not understand this type...", "So far, I am only able to understand these types:",
            "Todo, Deadline, Event, List, Delete, Done, Bye"};
        showMessage(msg);
    }

    /**
     * Prints invalid index error, to be shown when index does not exist/is more than size of task list.
     */
    public void showInvalidIndexError() {
        String[] msg = {"Oh no, this index does not exist!", "Please make sure that index < size of tasks"};
        showMessage(msg);
    }

    /**
     * Prints top divider and remaining strings with indent at the start.
     *
     * @param strings Strings to be printed.
     */
    public void showListHeader(String[] strings) {
        System.out.println(DIVIDER);
        for (String string : strings) {
            System.out.println(INDENT + string);
        }
    }

    /**
     * Prints the entire tasks list when user uses "find" command.
     *
     * @param taskList Task list to be printed.
     * @param keyword Search task list and only print items that matches the keyword.
     */
    public void showList(TaskList taskList, String keyword) {
        System.out.println(DIVIDER);
        if (taskList.getSize() > 0) {
            printLine("This is what I have found based on the keyword: " + keyword);
            for (int i = 0; i < taskList.getSize(); i++) {
                int taskNo = i + 1;
                Task curr = taskList.getTask(i);
                printLine(taskNo + "." + curr.toString());
            }
        } else {
            printLine("Oops, based on your keyword: " + keyword + ", I am not able to find any match :(");
        }
        System.out.println(DIVIDER);
    }
}
