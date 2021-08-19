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
            Task newTask = new Task(userInput, false);
            tasks.add(newTask);
            System.out.println("Duke: Added Task " + userInput);
            System.out.println("\n ----------------------------------");
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
