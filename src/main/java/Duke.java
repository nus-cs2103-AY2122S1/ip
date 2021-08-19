import java.util.Scanner;
import java.util.ArrayList;
public class Duke {
    public static ArrayList<String> tasks = new ArrayList<>();
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
            tasks.add(userInput);
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
