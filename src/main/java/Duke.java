import java.util.ArrayList;
import java.util.Scanner;  // Import the Scanner class

public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> todo = new ArrayList<>();
        String line = "-------------------------------------";
        System.out.println(line + "\n" + "Good Morning Master Wayne, Alfred here.\nWhat can I do for you today?\n" + line);

        String command = scanner.nextLine();
        while (!command.equals("bye")) {
            if(command.equals("list")) {
                for(int i = 0; i < todo.size(); i++) {
                    System.out.println(i + ". " + todo.get(i));
                }
                command = scanner.nextLine();
            } else {
                System.out.println(line + "\n" + "Understood, Master Wayne. Added: " + command + "\n" + line);
                todo.add(command);
                command = scanner.nextLine();
            }
        };
        System.out.println("Have a pleasant day, Master Wayne.\n");
    }
}
