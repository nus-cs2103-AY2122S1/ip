import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner myObj = new Scanner(System.in);

        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");

        boolean exit = false;
        Task[] tasks = new Task[100];
        int current = 0;

        while (!exit) {
            String userInput = myObj.nextLine();

            if (userInput.equals("bye")) {
                System.out.println("____________________________________________________________");
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println("____________________________________________________________");
                exit = true;
            } else if (userInput.equals("list")) {
                System.out.println("____________________________________________________________");
                for (int i = 0; i < current; i++) {
                    System.out.println(tasks[i].getTask());
                }
                System.out.println("____________________________________________________________");
            } else if (isDoneCall(userInput)) {
                int index = Integer.parseInt(userInput.substring(5));
                System.out.println("____________________________________________________________");
                if (tasks[index - 1] != null) {
                    tasks[index - 1].markAsDone();
                    System.out.println("Nice! I've marked this task as done: ");
                    System.out.println("  " + tasks[index - 1].getTaskNoNum());
                } else {
                    System.out.println("There is no such task.");
                }
                System.out.println("____________________________________________________________");
            } else {
                System.out.println("____________________________________________________________");
                try {
                    parseInput(userInput, tasks, current);
                    current++;
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
                System.out.println("____________________________________________________________");
            }
        }
    }
    public static boolean isDoneCall (String strNum) {
        if (strNum == null) {
            return false;
        }
        if (strNum.length() < 6) {
            return false;
        }
        try {
            int d = Integer.parseInt(strNum.substring(5));
        } catch (NumberFormatException nfe) {
            return false;
        }
        if (!strNum.startsWith("done ")) {
            return false;
        }
        return true;
    }

    public static void parseInput(String userInput, Task[] tasks, int current) throws IllegalArgumentException {
        if (userInput.startsWith("todo")) {
            if (userInput.substring(4).trim().isEmpty()) {
                throw new IllegalArgumentException(" ☹ OOPS!!! The description of a todo cannot be empty.");
            }
            tasks[current] = new Todo(userInput.substring(5), current + 1);
            System.out.println("Got it. I've added this task:");
            System.out.println("  " + tasks[current].getTaskNoNum());
            System.out.println("Now you have " + (current + 1) + " tasks in the list.");
        } else if (userInput.startsWith("deadline")) {
            if (userInput.substring(8).trim().isEmpty()) {
                throw new IllegalArgumentException(" ☹ OOPS!!! The description of a deadline cannot be empty.");
            }
            int slash = userInput.indexOf("/by");
            if (slash == -1) {
                throw new IllegalArgumentException(" Please set a deadline by adding /by");
            }
            tasks[current] = new Deadline(userInput.substring(9, slash), current + 1, userInput.substring(slash + 4));
            System.out.println("Got it. I've added this task:");
            System.out.println("  " + tasks[current].getTaskNoNum());
            System.out.println("Now you have " + (current + 1) + " tasks in the list.");
        } else if (userInput.startsWith("event")) {
            if (userInput.substring(5).trim().isEmpty()) {
                throw new IllegalArgumentException(" ☹ OOPS!!! The description of an event cannot be empty.");
            }
            int slash = userInput.indexOf("/at");
            if (slash == -1) {
                throw new IllegalArgumentException(" Please set a deadline by adding /at");
            }
            tasks[current] = new Event(userInput.substring(6, slash), current + 1, userInput.substring(slash + 4));
            System.out.println("Got it. I've added this task:");
            System.out.println("  " + tasks[current].getTaskNoNum());
            System.out.println("Now you have " + (current + 1) + " tasks in the list.");
        } else {
            throw new IllegalArgumentException(" ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }
}
