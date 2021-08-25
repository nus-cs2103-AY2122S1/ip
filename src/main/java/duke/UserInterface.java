package duke;

import java.util.Scanner;

public class UserInterface {
    Scanner sc;

    UserInterface() {

        this.sc = new Scanner(System.in);
    }

    public String nextCommand() {

        return sc.nextLine();
    }

    public void printInitialGreeting() {
        printLogo();
        System.out.println("Hello I'm Duke\n" + "What can I do for you?");
    }

    public void printGoodByeGreeting() {

        System.out.println("Bye. Hope to see you again soon!");
    }

    public void showError(DukeException e) {

        System.out.println("OOPS!!! " + e.getMessage());
    }

    public void showResult(CommandResult commandResult) {
        System.out.println(commandResult.getFeedbackResult());
    }

    private void printLogo() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
    }
}
