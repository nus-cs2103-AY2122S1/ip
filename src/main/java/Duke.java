import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {

        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> taskArr = new ArrayList<Task>();
        String command = sc.nextLine();
        while (! command.equals("bye")) {
            try {
                addTask(command, taskArr);
                
            } catch (DukeException e) {
                System.out.println(e);
            } finally {
                command = sc.nextLine();
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
    public static void addTask(String command, ArrayList<Task> taskArr) throws DukeException{
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
        } else if (commandArr[0].equals("delete")) {
            int taskArrRef = Integer.parseInt(commandArr[1]) - 1;
            Task taskRef = taskArr.get(taskArrRef);
            taskArr.remove(taskArrRef);
            System.out.println("Noted. I've removed this task:\n"+ taskRef);
            System.out.println("Now you have " + taskArr.size() + " tasks in the list.");
            
        } else {
            
            boolean commandArrayLength = commandArr.length <= 1;
            String taskAdded = "Got it. I've added this task: ";
            if (commandArr[0].equals("todo")) {
                if (commandArrayLength) {
                    throw new DukeException("The description of a todo cannot be empty!");
                } else {
                    System.out.println(taskAdded);
                    Task t = new Todo(command);
                    taskArr.add(t);
                    System.out.println(t);
                }

            } else if (commandArr[0].equals("deadline")) {
                if (commandArrayLength) {
                    throw new DukeException("The description of a deadline cannot be empty!");
                } else {
                    System.out.println(taskAdded);
                    int spaceIndex = command.indexOf(" ");
                    int slashIndex = command.indexOf("/");
                    Task t = new Deadline(command.substring(spaceIndex + 1, slashIndex - 1), command.substring(slashIndex + 4));
                    taskArr.add(t);
                    System.out.println(t);
                }

            } else if (commandArr[0].equals("event")) {
                if (commandArrayLength) {
                    throw new DukeException("The description of an event cannot be empty!");
                } else {
                    System.out.println(taskAdded);
                    int spaceIndex = command.indexOf(" ");
                    int slashIndex = command.indexOf("/");
                    Task t = new Event(command.substring(spaceIndex + 1, slashIndex - 1), command.substring(slashIndex + 4));
                    taskArr.add(t);
                    System.out.println(t);
                }

            } else {
                throw new DukeException("I'm sorry, but I don't know what that means!");
            }
            System.out.println("Now you have " + taskArr.size() + " tasks in the list.");

        }



    }
}
