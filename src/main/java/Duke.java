import java.util.ArrayList;
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
        printDoubleLineBorder();
        System.out.println("Hi there! I'm Dean" + "\nHow may I help you?");
        printDoubleLineBorder();

        Scanner scanner = new Scanner(System.in);  // Create a Scanner object

        while (scanner.hasNextLine()) {
            String input = scanner.nextLine();
            printSingleLineBorder();
            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
            } else if (input.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < numberOfTasks; i++) {
                    System.out.println((i + 1) + "." + list.get(i));
                }
            } else {
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
                        System.out.println("Invalid Task Index");
                    }
                } else if (splitInput[0].equals("delete")) {
                    try {
                        removeTask(splitInput[1]);
                    } catch (DukeException e) {
                        System.out.println(e.getMessage());
                    }
                } else {
                    try {
                        addTask(input);
                    } catch (DukeException e) {
                        System.out.println(e.getMessage());
                    }
                }
            }
            printSingleLineBorder();
        }
    }

    // Add task to list.
    private static void addTask(String input) throws DukeException {
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
                if (taskHasTimestamp) {
                    taskDescription += ")";
                }
            }
        }
        if (taskDescription.length() == 0) {
            throw new DukeException("The description of a task cannot be empty.");
        }
        if (taskType.equals("todo")) {
            task = new ToDo(taskDescription);
            list.add(task);
        } else if (taskType.equals("deadline")) {
            if (!input.contains("by")) {
                throw new DukeException("Invalid deadline task.\n" +
                        "Please enter task followed by /by and then the date/time it is due,\n" +
                        "e.g. CS2103T Individual Project /by Week 6");
            }
            task = new Deadline(taskDescription);
            list.add(task);
        } else if (taskType.equals("event")) {
            if (!input.contains("at")) {
                throw new DukeException("Invalid event task.\n" +
                        "Please enter task followed by /at and then the date it is due,\n" +
                        "e.g. CS2103T Test /at Friday 4-6pm");
            }
            task = new Event(taskDescription);
            list.add(task);
        } else {
            throw new DukeException("Invalid type of task. Only todo, deadline or event is allowed.");
        }
        numberOfTasks++;
        System.out.println("Got it. I've added this task:\n" + task);
        if (numberOfTasks == 1) {
            System.out.println("Now you have " + numberOfTasks + " task in the list");
        } else {
            System.out.println("Now you have " + numberOfTasks + " tasks in the list");
        }
    }

    // Remove task from list
    private static void removeTask(String index) throws DukeException {
        int taskIndex = Integer.parseInt(index) - 1;
        if (taskIndex < 0 || taskIndex > numberOfTasks - 1) {
            throw new DukeException("Invalid Task Index.");
        } else {
            Task task = list.get(taskIndex);
            list.remove(taskIndex);
            numberOfTasks--;
            System.out.println("Noted. I've removed the following task:\n" + task +
                    "\nNow you have " + numberOfTasks + " tasks in the list.");
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
