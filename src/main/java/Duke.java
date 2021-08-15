import java.util.ArrayList;
import java.util.Scanner;  // Import the Scanner class

public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> todo = new ArrayList<>();
        String line = "-------------------------------------";
        System.out.println(line + "\n" + "Good Morning Master Wayne, Alfred here.\nWhat can I do for you today?\n" + line);

        String command = scanner.nextLine();
        while (!command.equals("bye")) {
            if(command.equals("list")) {
                System.out.println(line);
                for(int i = 0; i < todo.size(); i++) {
                    Task task = todo.get(i);
                    System.out.println((i+1) + ". " + task.getStatusIcon() + " " + task.toString());
                }
                System.out.println(line);
                command = scanner.nextLine();
            } else if (command.contains("done")) {
                System.out.println(line);
                String[] splitup = command.split(" ");
                int index = Integer.parseInt(splitup[1]);
                todo.get(index - 1).markAsDone();
                System.out.println("Very well, Master Wayne. This task has been marked as per your request.");
                System.out.println((index) + ".[X] " + todo.get(index - 1)); //actual index is index - 1
                System.out.println(line);
                command = scanner.nextLine();

            } else {
                System.out.println(line + "\n" + "Understood, Master Wayne. Added: " + command + "\n" + line);
                Task t = new Task(command);
                todo.add(t);
                command = scanner.nextLine();
            }
        };
        System.out.println("Have a pleasant day, Master Wayne.\n");
    }
}
