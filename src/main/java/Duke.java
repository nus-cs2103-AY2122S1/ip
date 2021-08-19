import java.util.*;

public class Duke {
    static ArrayList<Task> userInputs = new ArrayList<>();
    static boolean userEnded = false;

    public static void markAsDone(String input) {
        try {
            System.out.println("Nice! I've marked this task as done: ");
            int taskIndex = Integer.parseInt(input.replaceAll("[^0-9]", ""));
            Task task = userInputs.get(taskIndex - 1);
            task.markAsDone();
            System.out.println(" " + task);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void List() {
        for (int i = 0; i < userInputs.size(); i++) {
            Task task = userInputs.get(i);
            System.out.println((i + 1) + ". " + task);
        }
    }

    public static void addTask(String input) throws DukeException {
        String description;
        String time;
        Task task;
        if (input.startsWith("todo")) {
            if (input.equals("todo")) {
                throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
            } else {
                description = input.split("todo ")[1];
                task = new Todo(description);
            }
        } else if (input.startsWith("deadline")) {
            if (input.equals("deadline")) {
                throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
            } else {
                description = input.split("deadline ")[1].split(" /")[0];
                time = input.split("/by ")[1];
                task = new Deadline(description, time);
            }
        } else if (input.startsWith("event")) {
            if (input.equals("event")) {
                throw new DukeException("☹ OOPS!!! The description of a event cannot be empty.");
            } else {
                description = input.split("event ")[1].split(" /")[0];
                time = input.split("/at ")[1];
                task = new Event(description, time);
            }
        } else {
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
        userInputs.add(task);
        System.out.println("Got it. I've added this task: ");
        System.out.println(task);
        System.out.println("Now you have " + userInputs.size() + " tasks in the list.");
    }

    public static void main(String[] args) {
        System.out.println("Hello! I'm Duke What can I do for you?");
        while (!userEnded) {
            Scanner sc = new Scanner(System.in);
            String str = sc.nextLine();
            if (str.equals("bye")) {
                userEnded = true;
            } else if (str.startsWith("done")) {
                markAsDone(str);
            } else if (str.equals("list")) {
                List();
            } else {
                try {
                    addTask(str);
                } catch (DukeException e) {
                    System.out.println(e);
                }

            }
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
