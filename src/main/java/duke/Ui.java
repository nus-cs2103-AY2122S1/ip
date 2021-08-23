package duke;

import java.util.Scanner;

public class Ui {
    public void showLine() {
        System.out.println("\t ____________________________________________________________ \n");
    }

    public void showWelcome() {
        showLine();
        String s = "\t Hello! I'm Duke, your personal assistant. \n" +
                "\t What can I do for you? \n";
        System.out.println(s);
        showLine();
    }

    public void showResponse(String message) {
        System.out.println("\t " + message + " \n");
    }

    public String readCommand() {
        Scanner s = new Scanner(System.in);
        return s.nextLine();
    }
}
