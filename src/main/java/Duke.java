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
                if (userInput.startsWith("todo ")) {
                    tasks[current] = new Todo(userInput.substring(5), current + 1);
                    System.out.println("____________________________________________________________");
                    System.out.println("Got it. I've added this task:");
                    System.out.println("  " + tasks[current].getTaskNoNum());
                    System.out.println("Now you have " + (current + 1) + " tasks in the list.");
                    System.out.println("____________________________________________________________");
                    current++;
                } else if (userInput.startsWith("deadline ")) {
                    int slash = userInput.indexOf("/by");
                    tasks[current] = new Deadline(userInput.substring(9, slash), current + 1, userInput.substring(slash + 4));
                    System.out.println("____________________________________________________________");
                    System.out.println("Got it. I've added this task:");
                    System.out.println("  " + tasks[current].getTaskNoNum());
                    System.out.println("Now you have " + (current + 1) + " tasks in the list.");
                    System.out.println("____________________________________________________________");
                    current++;
                } else if (userInput.startsWith("event ")) {
                    int slash = userInput.indexOf("/at");
                    tasks[current] = new Event(userInput.substring(6, slash), current + 1, userInput.substring(slash + 4));
                    System.out.println("____________________________________________________________");
                    System.out.println("Got it. I've added this task:");
                    System.out.println("  " + tasks[current].getTaskNoNum());
                    System.out.println("Now you have " + (current + 1) + " tasks in the list.");
                    System.out.println("____________________________________________________________");
                    current++;
                } else {
                    System.out.println("____________________________________________________________");
                    System.out.println("Please prefix your task with deadline, todo, or event.");
                    System.out.println("____________________________________________________________");
                }
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
}
