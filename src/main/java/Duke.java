import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    private static Scanner sc;
    private static ArrayList<Task> list;

    public static void main(String[] args) {
        sc = new Scanner(System.in);
        list = new ArrayList<>();

        // Show the logo
        showLogo();

        // Gets the user input
        getInput();
    }

    private static void showLogo() {
        String logo = " _______       ___      _______   __     __   _\n"
                    + "|   ____|     / ^ \\     |   _  \\  \\ \\   / /  | |\n"
                    + "|  | ___     / /_\\ \\    |  |_|  |  \\ \\ / /   | |\n"
                    + "|  ||_  |   /  ___  \\   |  __  <    \\   /    |_|\n"
                    + "|  |__| |  /  /   \\  \\  |  | \\  \\    | |      _ \n"
                    + "|_______| /__/\t   \\__\\ |--|  \\--\\   |_|     |_|\n";

        printDoubleDivider();
        System.out.println("Hello! My name is\n" + logo);
        printDoubleDivider();
        System.out.println("How may I help you?");
        printDoubleDivider();
    }

    private static void getInput() {
        System.out.print("Input: ");
        handleInput(sc.nextLine());
    }

    private static void handleInput(String input) {
        printSingleDivider();

        if (input.equals("bye")) {

            System.out.println("Output: Goodbye! See you again!");
            printDoubleDivider();
            sc.close();
            return;

        } else if (input.equals("list")) {

            System.out.println("Output: This is your current list!\n");
            for (int i = 0; i < list.size(); i++) {
                System.out.println(i + 1 + ". " + list.get(i));
            }

        } else if (input.startsWith("done")) {

            markTaskDone(input);

        } else if (input.startsWith("delete")) {

            deleteTask(input);

        } else {
            Task newTask = null;

            try {
                if (input.startsWith("todo")) {
                    // Check if a task description is present
                    checkDescription(input, "todo");

                    // Set the todo
                    newTask = setTodo(input.substring(5));
                } else if (input.startsWith("deadline")) {
                    // Check if a task description is present
                    checkDescription(input, "deadline");

                    // Set the deadline
                    newTask = setDeadline(input.substring(9));
                } else if (input.startsWith("event")) {
                    // Check if a task description is present
                    checkDescription(input, "event");

                    // Set the event
                    newTask = setEvent(input.substring(6));
                } else {
                    throw new InvalidInputException();
                }
            } catch (NoDescriptionException | InvalidParamException | InvalidInputException e1) {
                System.out.println("Output: " + e1.getMessage());
            }

            // If there was no error, then add task. Else, skip this and get input again.
            if (newTask != null) addTask(newTask);
        }

        printDoubleDivider();
        getInput();
    }

    private static void addTask(Task newTask) {
        list.add(newTask);
        System.out.println("Output:\n\nYou have successfully added the following task:\n\n" +
                            "    " + newTask);
        System.out.println("\nYou now have " + list.size() + (list.size() == 1 ? " task " : " tasks ") + "in your list!");
    }

    private static void checkDescription(String input, String task) throws NoDescriptionException {
        if (input.split(" ").length == 1) throw new NoDescriptionException(task);
    }

    private static void markTaskDone(String input) {

        try {
            int markDone = Integer.parseInt(input.substring(5)) - 1;
            list.get(markDone).markAsDone();

            System.out.println("Output:\n\nYou have successfully marked this task as done:\n\n" +
                                "    " + list.get(markDone));
        } catch (StringIndexOutOfBoundsException | NumberFormatException e1) {
            System.out.println("Output: Please specify which task you would like to\n" +
                                "mark as done by adding a single number after 'done'!\n" +
                                "i.e. done 1");
        } catch (IndexOutOfBoundsException e2) {
            System.out.println("Output: There is no task under that number!");
        }

    }

    private static void deleteTask(String input) {

        try {
            int toDelete = Integer.parseInt(input.substring(7)) - 1;
            Task deletedTask = list.remove(toDelete);

            System.out.println("Output:\n\nYou have successfully deleted this task:\n\n" +
                                "    " + deletedTask);
            System.out.println("\nYou now have " + list.size() + (list.size() == 1 ? " task " : " tasks ") + "in your list!");
        } catch (StringIndexOutOfBoundsException | NumberFormatException e1) {
            System.out.println("Output: Please specify which task you would like to\n" +
                                "delete by adding a single number after 'delete'!\n" +
                                "i.e. delete 1");
        } catch (IndexOutOfBoundsException e2) {
            System.out.println("Output: There is no task under that number!");
        }

    }

    private static Task setTodo(String input) {
        Task todo = new Todo(input);
        return todo;
    }

    private static Task setDeadline(String input) throws InvalidParamException {
        String[] deadlineParams = input.split(" /by ");
        if (deadlineParams.length != 2) {
            throw new InvalidParamException("Please include the deadline of the task after\n" +
                                            "a task description using a '/by' (only once).\n" +
                                            "i.e. deadline return book /by Monday");
        }
        Task deadline = new Deadline(deadlineParams[0], deadlineParams[1]);
        return deadline;
    }

    private static Task setEvent(String input) throws InvalidParamException {
        String[] eventParams = input.split(" /at ");
        if (eventParams.length != 2) {
            throw new InvalidParamException("Please include the time of the event after\n" +
                                            "a task description using an '/at' (only once).\n" +
                                            "i.e. event project meeting /at Aug 6th 2-4pm");
        }
        Task event = new Event(eventParams[0], eventParams[1]);
        return event;
    }

    private static void printDoubleDivider() {
        System.out.println("\n=================================================\n");
    }

    private static void printSingleDivider() {
        System.out.println("\n- - - - - - - - - - - - - - - - - - - - - - - - -\n");
    }

}

