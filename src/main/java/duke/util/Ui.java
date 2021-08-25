package duke.util;

import java.util.Scanner;

public class Ui {
    private final Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public void respond(String message) {
        System.out.println("\n============Duke says============");
        System.out.println(message);
        System.out.println("=================================");
    }

    public String nextCommand() {
        return scanner.nextLine();
    }
    public void greet() {
        System.out.println("Look at me! I'm DUKE\n"  + "How can I help?");
    }
    public void promptNext() {
        System.out.print("Whats next?");
    }
    public void clarify() {
        this.respond("Say something I can understand!! >:(");
    }
}
