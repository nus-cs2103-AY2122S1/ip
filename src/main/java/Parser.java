import java.util.Scanner;

public class Parser {

    public static void parse(String input, Ui ui, Storage storage, TaskList tasklist) {

    }
    public static void invalidInputResponse() {
        System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    public static void byeOutput() {
        System.out.println("Bye. Hope to see you again!");
    }

    public void listOutput() {

    }
    public static void processOutput(String in) {
        if (in.equals("bye")) {
            System.out.println("Bye. Hope to see you again!");
        }
        System.out.println("die");
    }
}
