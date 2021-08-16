import java.util.Scanner;

public class Chatbot {
    private final String name;
    private Storage storage;

    Chatbot(String name) {
        this.name = name;
        this.storage = new Storage();
    }

    void initialize() {
        greet();
        listen();
    }

    void greet() {
        printDashedLine();
        System.out.printf("Hey there! I'm %s%n", this.name);
        System.out.println("How can I help you?");
        printDashedLine();
    }

    void listen() {
        Scanner sc = new Scanner(System.in);
        String input;
        boolean isListening;

        do {
            input = sc.nextLine();
            isListening = parseInput(input);
        } while (isListening);
    }

    boolean parseInput(String input) {
        printDashedLine();
        if (input.equals("bye")) {
            System.out.println("Goodbye human. See you soon!");
            return false;
        } else if (input.equals("list")) {
            storage.listItems();
            printDashedLine();
            return true;
        }

        this.storage = storage.add(input);
        System.out.printf("Item succesfully added: %s%n", input);
        printDashedLine();

        return true;
    }

    void printDashedLine() {
        System.out.println("-----------------------------------------------------------------------------------------");
    }
}
