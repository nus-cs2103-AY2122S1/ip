import java.util.Scanner;

public class Duke {
    private static int listSize = 0;
    private static Task[] list = new Task[100]; // List to store all the tasks

    public static void handleInput(String input) {
        if (input.equals("list")) {
            for (int i = 0; i < listSize; i++) {
                System.out.println((i + 1) + "." + list[i]);
            }
        } else if (input.startsWith("done ")) {
            try {
                completeTask(input);
            } catch (NumberFormatException e) {
                addTask(input);
            }
        } else {
            addTask(input);
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
        if (escapePos < 0) throw new IllegalArgumentException("Please provide a time");
        return escapePos;
    }

    public static void completeTask(String input) {
        int completedTaskNumber = Integer.parseInt(input.substring(5)) - 1;
        list[completedTaskNumber].complete();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(list[completedTaskNumber]);
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
