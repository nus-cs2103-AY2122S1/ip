import java.util.Scanner;
import java.util.ArrayList;
public class Duke {
    public static ArrayList<Task> tasks = new ArrayList<>();
    public static void talk() {
        String userInput = "";
        System.out.println("Hello, What Can I do for you ?\n -------------------------------");

        while(!userInput.equals("bye")) {
            System.out.println("Enter Input Here: ");
            Scanner scanner = new Scanner(System.in);
            userInput = scanner.nextLine();
            if(userInput.equals("bye")) {
                System.out.println("Duke : Bye, Hope to see you again soon !");
                break;
            }
            if(userInput.equals("list")) {
                int numberOfTasks = tasks.size();
                for(int i = 0; i < numberOfTasks; i++) {
                    System.out.println(i + 1 + ". " + tasks.get(i));
                }
                System.out.println("\n ----------------------------------");
                continue;
            }
            if(userInput.startsWith("done")) {
                int id = Integer.valueOf(userInput.substring(5));
                Task currTask = tasks.get(id - 1);
                currTask.markAsDone();
                System.out.println("Nice! I've marked this task as done: \n" + currTask);
                System.out.println("\n ----------------------------------");
                continue;
            }
            Task newTask = handleInput(userInput);
            tasks.add(newTask);
            System.out.println("Duke: Added Task " + userInput);
            System.out.println("\n ----------------------------------");
        }
    }
    public static Task handleInput(String userInput) {
        if(userInput.startsWith("todo")) {
            int id = userInput.indexOf("todo") + 4;
            String task = userInput.substring(id);
            return new Todo(task, false);
        } else if(userInput.startsWith("deadline")) {
            int start_id = userInput.indexOf("deadline");
            int task_id = userInput.indexOf("/by");
            String task = userInput.substring(start_id + 9, task_id);
            String date = userInput.substring(task_id + 4);
            return new Deadline(task, false, date);
        } else if(userInput.startsWith("event")) {
            int start_id = userInput.indexOf("event");
            int task_id = userInput.indexOf("/at");
            String task = userInput.substring(start_id + 6, task_id);
            String date = userInput.substring(task_id + 4);
            return new Event(task, false, date);
        } else {
            return new Task(userInput, false);
        }
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        Duke.talk();
    }
}
