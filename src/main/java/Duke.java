import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?");

        Scanner sc = new Scanner(System.in);
        ArrayList<String> taskList = new ArrayList<>();

        while (sc.hasNextLine()) {
            String userInput = sc.nextLine();

            if (userInput.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (userInput.equals("list")) {
                //Print the list here
                for (int i = 0; i < taskList.size(); i += 1) {
                    System.out.println((i + 1) + ". " + taskList.get(i));
                }
            } else {
                //Add userInput to taskList
                taskList.add(userInput);
                //Print out confirmation message
                System.out.println("added: " + userInput);
            }
        }
        sc.close();
    }
}