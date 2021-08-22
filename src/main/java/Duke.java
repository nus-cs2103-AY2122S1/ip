import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    
    public static ArrayList<Task> taskArr;
    
    public static void main(String[] args) {

        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        Scanner sc = new Scanner(System.in);
        String command = sc.nextLine();
        taskArr = new ArrayList<>();
        while (! command.equals("bye")) {
            try {
                addTask(command);
                
            } catch (DukeException e) {
                System.out.println(e);
            } finally {
                command = sc.nextLine();
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
    public static void addTask(String command) throws DukeException{
        String[] commandArr = command.split(" ");
        if (command.equals("list")) {
            
            listCommand();
            
        } else if (commandArr[0].equals("done")) {
            
            int taskArrRef = Integer.parseInt(commandArr[1]) - 1;
            doneCommand(taskArrRef);
            
        } else if (commandArr[0].equals("delete")) {
            int taskArrRef = Integer.parseInt(commandArr[1]) - 1;
            deleteCommand(taskArrRef);
        } else {
            
            boolean commandArrayLength = commandArr.length <= 1;
            String taskAdded = "Got it. I've added this task: ";
            if (commandArr[0].equals("todo")) {
                if (commandArrayLength) {
                    throw new DukeException("The description of a todo cannot be empty!");
                } else {
                    System.out.println(taskAdded);
                    int spaceIndex = command.indexOf(" ");
                    Task t = new Todo(command.substring(spaceIndex + 1));
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
    
    public static void listCommand() {
        System.out.println("Here are the tasks in your list:");
        for(int i = 0; i < taskArr.size(); i++) {
            int j = i + 1;
            System.out.println(j + ". " + taskArr.get(i));
        }
    }
    
    public static void doneCommand(int index) throws DukeException{
        if (index >= taskArr.size() || index < 0) {
            throw new DukeException("Invalid value!");
        } else {
            Task taskRef = taskArr.get(index);
            taskRef.taskDone();
            System.out.println("Nice! I've marked this task as done:\n"+ taskRef);
        }
    }

    public static void deleteCommand(int index) throws DukeException{
        if (index >= taskArr.size() || index < 0) {
            throw new DukeException("Invalid value!");
        } else {
            Task taskRef = taskArr.get(index);
            taskArr.remove(index);
            System.out.println("Noted. I've removed this task:\n"+ taskRef);
            System.out.println("Now you have " + taskArr.size() + " tasks in the list.");
        }
    }
    
}
