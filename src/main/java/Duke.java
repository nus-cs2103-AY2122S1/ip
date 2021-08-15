import java.util.ArrayList;
import java.util.Scanner;  // Import the Scanner class

public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> todo = new ArrayList<>();
        ArrayList<Boolean> done = new ArrayList<>();
        String line = "-------------------------------------";
        System.out.println(line + "\n" + "Good Morning Master Wayne, Alfred here.\nWhat can I do for you today?\n" + line);

        String command = scanner.nextLine();
        while (!command.equals("bye")) {
            if(command.equals("list")) {
                for(int i = 0; i < todo.size(); i++) {
                    String task = todo.get(i);
                    boolean completed = done.get(i);

                    if (completed) {
                        System.out.println((i+1) + ".[X] " + task);
                    } else {
                        System.out.println((i+1) + ".[] " + task);
                    }
                }
                command = scanner.nextLine();
            } else if (command.contains("done")) {
                String[] splitup = command.split(" ");
                int index = Integer.parseInt(splitup[1]);
                done.set(index - 1, true); //actual index is index - 1
                System.out.println("Very well, Master Wayne. This task has been marked as per your request.");
                System.out.println((index) + ".[X] " + todo.get(index - 1)); //actual index is index - 1
                command = scanner.nextLine();

            } else {
                System.out.println(line + "\n" + "Understood, Master Wayne. Added: " + command + "\n" + line);
                todo.add(command);
                done.add(false);
                command = scanner.nextLine();
            }
        };
        System.out.println("Have a pleasant day, Master Wayne.\n");
    }
}
