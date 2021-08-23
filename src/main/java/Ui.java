import java.util.Scanner;

public class Ui {
    private final Scanner inputScanner;

    public Ui() {
        this.inputScanner = new Scanner(System.in);
        greetOnStart();
    }

    public void greetOnStart() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
    }

    public String getNextInput() {
        return inputScanner.nextLine();
    }

    public void closeInput() {
        inputScanner.close();
    }

    public void showInvalidSelectionError() {
        System.out.println("\tSorry, I can't seem to find that task\n");
    }

    public void showNumberFormatException() {
        System.out.println("\tI'm Sorry, WHAT?!?!\n");
    }

    public void showEmptyInputException() {
        System.out.println("\tTake your time :)\n");
    }

    public void showUnknownCommandException() {
        System.out.println("\tI don't understand that command (yet...)\n");
    }

    public void showDukeException(String message) {
        System.out.println(message);
    }

    public void printTaskList(TaskList tasks) {
        if (tasks.isEmpty()) {
            System.out.println("\tYou haven't added any tasks yet\n");
        } else {
            System.out.println(tasks);
        }
    }

    public void greetWithFamiliarity(TaskList tasks) {
        System.out.println("\tNice to see you again.");
        System.out.println(tasks.taskSummary());
        if (!tasks.isEmpty()) {
            System.out.println(tasks);
        }
    }
}
