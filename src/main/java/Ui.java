import java.util.Scanner;

public class Ui {
    private final static String FORMAT = "\t%s\n";
    private final static String INDENTED_FORMAT = "\t\t%s\n";
    private final static String LINE = "______________________________________________________";
    private final static String LOGO =
            "\t ____        _        \n"
            + "\t|  _ \\ _   _| | _____ \n"
            + "\t| | | | | | | |/ / _ \\\n"
            + "\t| |_| | |_| |   <  __/\n"
            + "\t|____/ \\__,_|_|\\_\\___|\n";

    private final Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public void showIntroduction() {
        System.out.print(LOGO);
        System.out.printf(FORMAT, LINE);
        System.out.printf(FORMAT, "Hello there, I'm Duke!");
        System.out.printf(FORMAT, "What can I do for you today?");
        System.out.printf(FORMAT, LINE);
    }

    public void showLine() {
        System.out.printf(FORMAT, LINE);
    }

    public void showMessage(String message) {
        System.out.printf(FORMAT, message);
    }

    public void showIndentedMessage(String message) {
        System.out.printf(INDENTED_FORMAT, message);
    }

    public void showError(String errorMessage) {
        System.out.printf("\tUh-oh! %s\n", errorMessage);
    }

    public void showFileNotFoundError() {
        System.out.printf(FORMAT, "This appears to be your first time using Duke.");
        System.out.printf(FORMAT, "A save file will be created to save your tasks when you first add a task.");
    }

    public void showLoadingError(String errorMessage) {
        this.showError(errorMessage);
        System.out.printf(FORMAT, "This appears to be an error with your save file.");
        System.out.printf(FORMAT, "Either edit data/tasks.txt to rectify the error, or delete it.");
        System.out.printf(FORMAT, "For now, you'll start with an empty task list.");
    }

    public String readInput() {
        return scanner.nextLine().trim();
    }

}
