package duke.util;

import java.util.Scanner;

public class Ui {
    private Scanner scanner;

    public Ui() {
        scanner = new Scanner(System.in);
    }

    public void showWelcome() {
        String welcome = "    ____________________________________________________________\n"
                + "     Hello! I'm Duke\n"
                + "     What can I do for you?\n"
                + "    ____________________________________________________________\n";
        System.out.println(welcome);
    }

    public String readCommand() {
        String nextLine = scanner.nextLine();
        while (nextLine.equals("")) {
            nextLine = scanner.nextLine();
        }
        return nextLine;
    }

    public void showError(String errorMsg) {
        System.out.println("     " + errorMsg);
    }

    public void showOpeningLine() {
        System.out.println("    ____________________________________________________________");
    }

    public void showClosingLine() {
        System.out.println("    ____________________________________________________________\n");
    }

    public void showResponse(String response) {
        System.out.println("     " + response);
    }

    public void closeScanner() {
        scanner.close();
    }
}
