import java.util.Scanner;

public class Chatbot {
    private final String name;
    private final TaskList taskList;

    Chatbot(String name) {
        this.name = name;
        this.taskList = new TaskList();
    }

    void initialize() {
        greet();
        listen();
    }

    void greet() {
        printDashedLine();
        System.out.printf("Hey there! I'm %s%n", name);
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
            listItems();
            return true;
        } else if (input.contains("done")) {
            markAsDone(input);
            return true;
        }

        taskList.add(new Task(input));
        System.out.printf("Item successfully added: %s%n", input);
        printDashedLine();

        return true;
    }

    void listItems() {
        taskList.listItems();
        printDashedLine();
    }

    void markAsDone(String input) {
        int index = Integer.parseInt(input.split(" ")[1]);
        taskList.markTaskAsDone(index);
        printDashedLine();
    }

    void printDashedLine() {
        System.out.println("-----------------------------------------------------------------------------------------");
    }
}
