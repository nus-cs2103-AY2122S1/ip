package duke;

import java.util.Scanner;

public class Ui {

    private String command;

    public void showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("-----------------------------------------");
        System.out.println(" Hello! I am Duke");
        System.out.println(" What can I do for you?");
        System.out.println("-----------------------------------------");
        System.out.println();
    }

    public void showError(String errorMessage) {
        System.out.println(errorMessage);
    }

    public void showLoadingError() {
        System.out.println("Something went wrong! Seems like I'm unable to load your file!");
    }

    public void showSavingError() {
        System.out.println("Something went wrong! Seems like I'm unable to save to your file!");
    }

    public String readCommand() {
        Scanner sc = new Scanner(System.in);
        this.command = sc.nextLine();
        return this.command;
    }

    public String getCommand() {
        return this.command;
    }

    public void showExit() {
        System.out.println("     Bye. Hope to see you again soon!");
    }

    public void showTopLine() {
        System.out.println("    -----------------------------------------");
    }

    public void showBottomLine() {
        System.out.println("    -----------------------------------------");
        System.out.println();
    }
}
