import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static ArrayList<Task> list;

    public static void main(String[] args) {
        list = new ArrayList<>();
        startDeanBot();
    }

    // Starts Dean bot.
    private static void startDeanBot() {
        String logo = "   ____                       \n"
                +     "  |  _ \\  ___  __ _ _ ___    \n"
                +     "  | | | |/ _ \\/ _` | '_  \\  \n"
                +     "  | |_| |  __| (_| | | | |    \n"
                +     "  |____/ \\___|\\__,_|_| |_|  \n";
        printDoubleLineBorder();
        System.out.println("Hello! I'm\n" + logo + "\nHow may I help you?");
        printDoubleLineBorder();

        Scanner scanner = new Scanner(System.in);  // Create a Scanner object

        while (scanner.hasNextLine()) {
            String input = scanner.nextLine();
            switch(input) {
                case "bye":
                    printSingleLineBorder();
                    System.out.println("Bye. Hope to see you again soon!");
                    printSingleLineBorder();
                    break;
                case "list":
                    printSingleLineBorder();
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < list.size(); i++) {
                        System.out.println((i + 1) + "." + list.get(i));
                    }
                    printSingleLineBorder();
                    break;
                default:
                    printSingleLineBorder();
                    String[] splitInput = input.split(" ");
                    if (splitInput[0].equals("done")) {
                        try {
                            int taskIndex = Integer.parseInt(splitInput[1]) - 1;
                            if (taskIndex < 0 || taskIndex > list.size() - 1) {
                                throw new NumberFormatException();
                            }
                            Task task = list.get(taskIndex);
                            task.markDone();
                            System.out.println("Nice! I've marked this task as done:");
                            System.out.println(task);
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid Task Number");
                        }
                    } else {
                        Task task = new Task(input);
                        list.add(task);
                        System.out.println("Added: " + input);
                    }
                    printSingleLineBorder();
            }
        }
    }

    // Print double line border.
    private static void printDoubleLineBorder() {
        System.out.println("==================================================");
    }

    // Prints single line border.
    private static void printSingleLineBorder() {
        System.out.println("--------------------------------------------------");
    }
}
