package duke.util;

import java.util.Scanner;

public class Ui {
    private Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public void showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println("Hello from\n" + logo);
    }

    public String readLine() {
        String input = scanner.nextLine();
        return input;
    }

    public void greeting(String name) {
        printHorizLine();
        System.out.println("Hello " + name + "!");
        System.out.println("I'm Duke");
        printHorizLine();
        System.out.println("");
    }

    public void sayBye(String name) {
        System.out.println("Bye " + name + ", hope to see you soon!");
    }

    public void printHorizLine() {
        System.out.println("————————————————————————————————————————");
    }

    public void printMsg(String msg) {
        System.out.println(msg);
    }

    public void showError(String errorMsg) {
        System.out.println(errorMsg);
    }

}
