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
            System.out.println("Duke: " + userInput + "\n ----------------------------------");
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
