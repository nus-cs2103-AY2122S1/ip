import java.util.Scanner;

public class Chatbot {
    String name;

    Chatbot(String name) {
        this.name = name;
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
        if (endListening(input)) {
            printDashedLine();
            System.out.println("Goodbye human. See you soon!");

            return false;
        }

        printDashedLine();
        System.out.println(input);
        printDashedLine();

        return true;
    }

    boolean endListening(String input) {
        return input.equals("bye");
    }

    void printDashedLine() {
        System.out.println("-----------------------------------------------------------------------------------------");
    }
}
