import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Duke {
    public static void main(String[] args) {
        List<String> savedTasks = new ArrayList<>();
        Scanner scn = new Scanner(System.in);
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke!\nWhat can I do for you today?");
        while (true) {
            String userInput = scn.nextLine();
            if (userInput.toLowerCase().equals("bye")) {
                System.out.println("Bye! Hope to see you again soon!");
                break;
            }
            else if (userInput.toLowerCase().equals("list")) {
                for (int i=0; i< savedTasks.size(); i++) {
                    int index = i+1;
                    System.out.println(index + ". " + savedTasks.get(i));
                }
            }
            else {
                System.out.println("added: " + userInput);
                savedTasks.add(userInput);
            }
        }
    }
}
