import java.util.Scanner;

public class Ui {
    protected static final String logo = "\t ____        _        \n"
            + "\t|  _ \\ _   _| | _____ \n"
            + "\t| | | | | | | |/ / _ \\\n"
            + "\t| |_| | |_| |   <  __/\n"
            + "\t|____/ \\__,_|_|\\_\\___|\n\n";

    private static final String divider = "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~";

    public void showLine() {
        System.out.println("\t" + Ui.divider);
    }

    public void showLoadingERROR() {
        this.showLine();
        System.out.println("\t☹ OOPS!!! File loading failed :-(");
        this.showLine();
    }

    public void showWelcome() {
        this.showLine();
        System.out.println("\tWelcome to");
        System.out.println(Ui.logo);
        System.out.println("\tI'm Desmond, how may I serve you? :)");
        this.showLine();
    }

    public String readCommand() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        scanner.close();
        return input;
    }

    public void showError(String errorMessage) {
        this.showLine();
        System.out.println("\tSomething went wrong: " + errorMessage);
        this.showLine();
    }
}
