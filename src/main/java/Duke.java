import java.util.Scanner;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class Duke {
    public static Task createTask(String input) throws DukeException {
        String[] inputList = input.split(" ");
        if (inputList[0].equals("todo")){
            String todoDescription = input.replaceFirst(Pattern.quote("todo"),"").trim();
            if (todoDescription.equals("")) {
                throw new DukeException("The description of a todo cannot be empty.");
            }
            return new Todo(todoDescription);
        } else if (inputList[0].equals("deadline")) {
            String[] withoutAction = input.replaceFirst(Pattern.quote("deadline"),"").split("/by", 2);
            return new Deadline(withoutAction[0].trim(), withoutAction[1].trim());
        } else {
            String[] withoutAction = input.replaceFirst(Pattern.quote("event"),"").split("/at", 2);
            return new Event(withoutAction[0].trim(), withoutAction[1].trim());
        }
    }
    public static void main(String[] args) {

        ArrayList<Task> tasks = new ArrayList<Task>();

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("What can I do for you?");
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        while(!input.equals("bye")) {
            String[] inputList = input.split(" ");
            String action = inputList[0];
            try {
                if (action.equals("list")) {
                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.println((i + 1) + "." + tasks.get(i).toString());
                    }
                } else if (action.equals("delete") || action.equals("done")) {
                    if (inputList.length != 2) {
                        throw new DukeException("Please provide the target task index!");
                    }
                    int taskIndex = Integer.parseInt(inputList[1]);
                    if (tasks.size() < taskIndex || 0 >= taskIndex) {
                        throw new DukeException("Invalid task index provided!");
                    }
                    if (action.equals("done")) {
                        Task currTask = tasks.get(taskIndex - 1);
                        currTask.markAsDone();
                        System.out.println("Nice! I've marked this task as done:\n" + currTask);
                    } else {
                        Task removedTask = tasks.remove(taskIndex - 1);
                        System.out.println("Noted. I've removed this task:");
                        System.out.println(removedTask);
                        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                    }
                } else if (action.equals("todo") || action.equals("deadline") || action.equals("event")) {
                    Task newTask = createTask(input);
                    tasks.add(newTask);
                    System.out.println("Got it. I've added this task:");
                    System.out.println(newTask);
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                } else {
                    throw new DukeException("I'm sorry, but I don't know what that means :-(");
                }

            } catch (DukeException e) {
                System.out.println("☹ OOPS!!! " + e.getMessage());
            }
            input = sc.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
