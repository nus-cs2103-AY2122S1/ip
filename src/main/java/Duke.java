import java.util.Scanner;

public class Duke {
    private static int listSize = 0;
    private static Task[] list = new Task[100]; // List to store all the tasks

    public static void handleInput(String input) {
        try {
            if (input.equals("list")) {
                for (int i = 0; i < listSize; i++) {
                    System.out.println((i + 1) + "." + list[i]);
                }
            } else if (input.startsWith("done ")) {
                completeTask(input);
            } else if (input.startsWith("delete ")) {
                deleteTask(input);
            } else {
                addTask(input);
            }
        } catch (IllegalArgumentException e) {
            System.out.println("OOPS!! " + e.getMessage());
        }
    }

    public static void addTask(String desc) throws IllegalArgumentException {
        Task newTask;
        if (desc.startsWith("todo ")) {
            newTask = new Todo(desc.substring(5));
        } else if (desc.startsWith("deadline ")) {
            int location = findEscape(desc);
            newTask = new Deadline(desc.substring(9, location - 1), desc.substring(location + 4));
        } else if (desc.startsWith("event ")) {
            int location = findEscape(desc);
            newTask = new Event(desc.substring(6, location - 1), desc.substring(location + 4));
        } else {
            throw new IllegalArgumentException("Please specify a type of task!");
        }
        list[listSize] = newTask;
        listSize++;
        System.out.println("added: " + newTask);
    }

    public static int findEscape(String desc) {
        int escapePos = desc.indexOf("/");
        if (escapePos < 0) throw new IllegalArgumentException("Please provide a duration or deadline.");
        return escapePos;
    }

    public static void completeTask(String input) {
        int completedTaskNumber;
        try {
            completedTaskNumber = Integer.parseInt(input.substring(5)) - 1;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid task number provided.");
        }
        if (completedTaskNumber < 0 || completedTaskNumber >= listSize) {
            throw new IllegalArgumentException("Task with that task number does not exist.");
        }
        list[completedTaskNumber].complete();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(list[completedTaskNumber]);
    }

    public static void deleteTask(String input) {
        int deletedTaskNumber;
        try {
            deletedTaskNumber = Integer.parseInt(input.substring(7)) - 1;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid task number provided.");
        }
        if (deletedTaskNumber < 0 || deletedTaskNumber >= listSize) {
            throw new IllegalArgumentException("Task with that task number does not exist.");
        }

        Task deletedTask = list[deletedTaskNumber];

        // Shift all the tasks 1 unit up
        for (int i = deletedTaskNumber; i < listSize; i++) {
            list[i] = list[i+1];
        }
        listSize--;

        System.out.println("Noted. I've removed this task:");
        System.out.println(deletedTask);
        System.out.println("Now you have " + listSize + " tasks in the list.");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");

        while (true) {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                // Exit loop and thus program when input is bye
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }
            handleInput(input);
        }
    }
}
