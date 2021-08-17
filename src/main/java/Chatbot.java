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
        Task newItem = null;
        try {
            if (input.contains("todo")) {
                String[] parsedInput = input.split(" ", 2); // Splits input into array of [todo, ...]
                if (isIncomplete(parsedInput)) {
                    throw new ToDoException();
                }
                newItem = new ToDo(parsedInput[1]);
            } else if (input.contains("deadline")) {
                String[] parsedInput = input.split(" ", 2); // Splits input into array of [deadline, ...]
                if (isIncomplete(parsedInput)) {
                    throw new DeadlineException();
                }

                String[] description = parsedInput[1].split("/");
                if (isIncomplete(description)) {
                    throw new DeadlineException("The deadline cannot be empty. Please provide a deadline for this task.");
                }

                String name = description[0];
                String deadline = description[1].split(" ")[1];
                newItem = new Deadline(name, deadline);
            } else if (input.contains("event")) {
                String[] parsedInput = input.split(" ", 2); // Splits input into array of [event, ...]
                if (isIncomplete(parsedInput)) {
                    throw new EventException();
                }

                String[] description = parsedInput[1].split("/");
                if (isIncomplete(description)) {
                    throw new EventException("The time of event cannot be empty. Please provide a time for this task.");
                }
                String name = description[0];
                String time = description[1].split(" ", 2)[1];
                newItem = new Event(name, time);
            } else {
                throw new HAL9000Exception("I'm sorry, but I do not quite understand what that means :(");
            }
        } catch (HAL9000Exception e) {
            System.out.println(e.getMessage());
            return;
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

    boolean isIncomplete(String[] arr) {
        return arr.length <= 1;
    }

    void printDashedLine() {
        System.out.println("-----------------------------------------------------------------------------------------");
    }
}
