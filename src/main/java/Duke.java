import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Duke {
    private static ArrayList<Task> list;
    private static int numberOfTasks;

    public static void main(String[] args) {
        list = new ArrayList<>();
        numberOfTasks = 0;
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
                    for (int i = 0; i < numberOfTasks; i++) {
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
                        addTask(input);

                    }
                    printSingleLineBorder();
            }
        }
    }

    // Add task to list.
    private static void addTask(String input) {
        Task task;
        String[] splitInput = input.split(" ");
        String taskType = splitInput[0];
        String taskDescription = "";
        boolean taskHasTimestamp = false;
        for (int i = 1; i < splitInput.length; i++) {
            if (splitInput[i].contains("/")) {
                taskHasTimestamp = true;
                taskDescription += "(" + splitInput[i].split("/")[1] + ": ";
            } else if (i != splitInput.length - 1){
                taskDescription += splitInput[i] + " ";
            } else {
                taskDescription += splitInput[i];
            }
        }
        if (taskHasTimestamp) {
            taskDescription += ")";
        }
        if (taskType.equals("todo")) {
            task = new ToDo(taskDescription);
            list.add(task);
        } else if (taskType.equals("deadline")) {
            task = new Deadline(taskDescription);
            list.add(task);
        } else if (taskType.equals("event")) {
            task = new Event(taskDescription);
            list.add(task);
        } else {
            throw new IllegalArgumentException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
        numberOfTasks++;
        System.out.println("Got it. I've added this task:\n" + task);
        if (numberOfTasks == 1) {
            System.out.println("Now you have " + numberOfTasks + " task in the list");
        } else {
            System.out.println("Now you have " + numberOfTasks + " tasks in the list");
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
