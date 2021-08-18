import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {

        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> taskArr = new ArrayList<Task>();
        String command = sc.nextLine();
        while (! command.equals("bye")) {
            addTask(command, taskArr);
            command = sc.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
    public static void addTask(String command, ArrayList<Task> taskArr) {
        String[] commandArr = command.split(" ");
        if (command.equals("list")) {
            System.out.println("Here are the tasks in your list:");
            for(int i = 0; i < taskArr.size(); i++) {
                int j = i + 1;
                System.out.println(j + ". " + taskArr.get(i));
            }
        } else if (commandArr[0].equals("done")) {
            int taskArrRef = Integer.parseInt(commandArr[1]) - 1;
            Task taskRef = taskArr.get(taskArrRef);
            taskRef.taskDone();
            System.out.println("Nice! I've marked this task as done:\n"+ taskRef);
        } else {
            System.out.println("Got it. I've added this task: ");
            if (commandArr[0].equals("todo")) {
                Task t = new Todo(command);
                taskArr.add(t);
                System.out.println(t);

            } else if (commandArr[0].equals("deadline")) {
                int spaceIndex = command.indexOf(" ");
                int slashIndex = command.indexOf("/");
                Task t = new Deadline(command.substring(spaceIndex + 1, slashIndex - 1), command.substring(slashIndex + 4, command.length()));
                taskArr.add(t);
                System.out.println(t);

            } else if (commandArr[0].equals("event")) {
                int spaceIndex = command.indexOf(" ");
                int slashIndex = command.indexOf("/");
                Task t = new Event(command.substring(spaceIndex + 1, slashIndex - 1), command.substring(slashIndex + 4, command.length()));
                taskArr.add(t);
                System.out.println(t);

            } else {
                Task t = new Task(command);
                taskArr.add(t);
                System.out.println("added: " + command);
            }
            System.out.println("Now you have " + taskArr.size() + " tasks in the list.");

        }



    }
}
