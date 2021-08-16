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
        } else if (input.contains("done")) {
            markAsDone(input);
        } else {
            addItem(input);
        }

        printDashedLine();
        return true;
    }

    void addItem(String input) {
        Task newItem;
        if (input.contains("todo")) {
            newItem = new ToDo(input.split(" ", 2)[1]);
        } else if (input.contains("deadline")) {
            String[] description = input.split(" ", 2)[1].split("/");
            String name = description[0];
            String deadline = description[1].split(" ")[1];
            newItem = new Deadline(name, deadline);
        } else {
            String[] description = input.split(" ", 2)[1].split("/");
            String name = description[0];
            String time = description[1].split(" ", 2)[1];
            newItem = new Event(name, time);
        }
        taskList.add(newItem);
    }

    void listItems() {
        taskList.listItems();
    }

    void markAsDone(String input) {
        int index = Integer.parseInt(input.split(" ")[1]);
        taskList.markTaskAsDone(index);
    }

    void printDashedLine() {
        System.out.println("-----------------------------------------------------------------------------------------");
    }
}
