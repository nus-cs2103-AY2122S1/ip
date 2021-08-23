package duke.ui;

import duke.data.TaskList;
import duke.data.task.Task;

import java.util.Scanner;

public class Ui {
    private static final String line = "__________________________________________________________";

    private final Scanner in;

    public Ui() {
        this.in = new Scanner(System.in);
    }

    public String readCommand() {
        return in.nextLine();
    }

    /**
     * Frames the message with underscore lines and prints it.
     *
     * @param msg The String we want to frame.
     */
    public void showFramedMsg(String msg) {
        System.out.println(line);
        System.out.println(msg);
        System.out.println(line);
        System.out.println();
    }

    /**
     * Prints the Duke logo.
     */
    public void showLogo() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|";
        showFramedMsg("Hello from\n" + logo);
    }

    /**
     * Prints our greeting.
     */
    public void showGreeting() {
        String greeting = "Hello! I'm Duke\n"
                + "What can I do for you?";
        showFramedMsg(greeting);
    }

    /**
     * Prints our welcome message.
     */
    public void showWelcome() {
        showLogo();
        showGreeting();
    }

    /**
     * Prints our goodbye.
     */
    public void showGoodBye() {
        String bye = "Bye! Hope to see you again soon!";
        showFramedMsg(bye);
    }

    /**
     * Prints out the tasklist.
     */
    public void showList(TaskList taskList) {
        System.out.println(line);
        System.out.println("Here are the tasks in your list:");
        int index = 1;
        for (Task task : taskList.getAllTasks()) {
            System.out.println((index++) + "." + task);
        }
        System.out.println(line);
        System.out.println();
    }

    public void showLoadingError() {
        String loadingError = "OOPS!!! We couldn't load your saved tasks!";
        showFramedMsg(loadingError);
    }

    public void showError(String msg) {
        showFramedMsg(msg);
    }
}
