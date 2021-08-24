import java.util.Scanner;

public class Ui {
    protected static final String logo = "\t ____        _        \n"
            + "\t|  _ \\ _   _| | _____ \n"
            + "\t| | | | | | | |/ / _ \\\n"
            + "\t| |_| | |_| |   <  __/\n"
            + "\t|____/ \\__,_|_|\\_\\___|\n\n";

    private static final String divider = "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~";

    private Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public void showLine() {
        System.out.println("\t" + Ui.divider);
    }

    public void showLoadingError() {
        System.out.println("\tNo record found.");
        System.out.println("\tInitializing...");
    }

    public void showWelcome() {
        this.showLine();
        System.out.println("\tWelcome to");
        System.out.println(Ui.logo);
        System.out.println("\tI'm Desmond, how may I serve you? :)");
        this.showLine();
    }

    public String readCommand() {
        String nextLine = this.scanner.nextLine();
        while (nextLine.equals("")) {
            nextLine = this.scanner.nextLine();
        }
        return nextLine;
    }

    public void showError(String errorMessage) {
        System.out.println("\tSomething went wrong: ");
        System.out.println("\t" + errorMessage);
    }
}
