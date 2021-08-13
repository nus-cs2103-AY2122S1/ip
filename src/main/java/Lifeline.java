import java.util.Scanner;
import java.util.ArrayList;

public class Lifeline {
    private Scanner sc;
    private ArrayList<String> list;
    private String command;

    Lifeline() {
        this.list = new ArrayList<>();
        this.sc = new Scanner(System.in);
    }

    private void greet() {
        String lifeline = " _      _____ ______ ______ _      _____ _   _ ______\n"
                + "| |    |_   _|  ____|  ____| |    |_   _| \\ | |  ____|\n"
                + "| |      | | | |__  | |__  | |      | | |  \\| | |__\n"
                + "| |      | | |  __| |  __| | |      | | | . ` |  __|\n"
                + "| |____ _| |_| |    | |____| |____ _| |_| |\\  | |____\n"
                + "|______|_____|_|    |______|______|_____|_| \\_|______|\n";
        System.out.println("Hello! I am\n" + lifeline);
        System.out.println("What can I help you with today?\n");
    }

    private void getInput() {
        this.command = sc.nextLine().trim();
        System.out.println();
        switch (command) {
        case "list":
            printList();
            break;
        case "bye":
            exit();
            break;
        default:
            addToList(command);
            break;
        }
    }

    private void printList() {
        for (int i = 0; i < list.size(); i++) {
            System.out.println((i + 1) + ". " + list.get(i));
        }
        System.out.println();
        getInput();
    }

    private void addToList(String input) {
        list.add(input);
        echo(input);
        getInput();
    }

    private void echo(String input) {
        System.out.println("You have said " + "\"" + input + "\".");
        System.out.println("Anything else?\n");
    }

    private void exit() {
        System.out.println("Goodbye! Thanks for chatting with me!\n");
    }

    public static void main(String[] args) {
        Lifeline lifeline = new Lifeline();
        lifeline.greet();
        lifeline.getInput();
    }
}
