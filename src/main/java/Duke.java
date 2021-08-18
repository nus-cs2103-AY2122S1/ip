import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String introMessage = "Hello! I'm Lawbringer!\n" +
                "What can i do for you?";
        System.out.println(introMessage);
        Scanner scanner = new Scanner(System.in);
        List<String> userList = new ArrayList<String>();
        while (true) {
            String userInput = scanner.nextLine();
            if (userInput.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (userInput.equals("list")) {
                int counter = 1;
                for (String text : userList) {
                    System.out.println(counter + ". " + text);
                    counter++;
                }
            } else {
                userList.add(userInput);
                System.out.println("added: " + userInput);
            }
        }
    }
}
