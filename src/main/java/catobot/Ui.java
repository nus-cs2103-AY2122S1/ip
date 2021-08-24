package catobot;

import java.util.Scanner;

public class Ui {
    private static final String name = "catobot.Catobot";
    private static final String banner = "(=^^=)(=^^=)(=^^=)(=^^=)";
    private static final String greeting
            = "Hello I am " + name + " (>^^<)\n    What can I do for you meow?";
    private static final String byeMessage
            = "Bye meow! I will always wait here meow(>^^<)";

    private Scanner sc;

    protected Ui() {
        this.sc = new Scanner(System.in);
    }

    public static void respond(String message) {
        String s = String.format("    %s\n    %s\n    %s", banner, message, banner);
        System.out.println(s);
    }

    /**
     * Sends an initialization message.
     */
    public void showWelcome() {
        respond(greeting);
    }

    public String readCommand() {
        return sc.nextLine();
    }

    /**
     * Sends an exit message.
     */
    public void exit() {
        respond(byeMessage);
        sc.close();
    }

    public static void showLoadingError() {
        respond("Meow! This is an error when loading the file! You will have a new task list meow >.<");
    }

    public void showError(String message) {
        respond(message);
    }

}
