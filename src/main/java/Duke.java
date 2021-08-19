import java.util.Scanner;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class Duke {
    public static Task createTask(String input) {
        String[] inputList = input.split(" ");
        if (inputList[0].equals("todo")){
            return new Todo(input.replaceFirst(Pattern.quote("todo "),""));
        } else if (inputList[0].equals("deadline")) {
            String[] withoutAction = input.replaceFirst(Pattern.quote("deadline "),"").split("/by", 2);
            return new Deadline(withoutAction[0].trim(), withoutAction[1].trim());
        } else {
            String[] withoutAction = input.replaceFirst(Pattern.quote("event "),"").split("/at", 2);
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
            switch (inputList[0]) {
                case "list":
                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.println((i + 1) + "." + tasks.get(i).toString());
                    }
                    break;
                case "done":
                    if (inputList.length != 2) {
                        System.out.println("Please provide the task index to mark as done!");
                        break;
                    }
                    int taskIndex = Integer.parseInt(inputList[1]);
                    if (tasks.size() < taskIndex || 0 >= taskIndex ) {
                        System.out.println("Invalid task index provided!");
                        break;
                    }
                    Task currTask = tasks.get(taskIndex - 1);
                    currTask.markAsDone();
                    System.out.println("Nice! I've marked this task as done:\n" + currTask);
                    break;
                case "todo":
                case "deadline":
                case "event":
                    Task newTask = createTask(input);
                    tasks.add(newTask);
                    System.out.println("Got it. I've added this task:");
                    System.out.println(newTask);
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                    break;
            }
            input = sc.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
