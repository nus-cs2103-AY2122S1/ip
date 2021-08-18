import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String bearPicStart = "┏ʕ •ᴥ•ʔ┛";
        String bearPicEnd = " ＼ʕ •ᴥ•ʔ／";
        List<Task> inputs = new ArrayList<Task>();

        System.out.println("Hi, I'm Duke the Bear! \n");
        System.out.println(bearPicStart + "\n");
        System.out.println("What do you want to do?");
        while (true) {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                System.out.println("Bye bye! Thank u Beary Much!\n");
                System.out.println(bearPicEnd);
                break;
            } else if (input.equals("list")) {
                System.out.println("Current List:");
                System.out.println("---------------");
                for (int i = 0; i < inputs.size(); i++) {
                    System.out.println( (i+1) + ": " + inputs.get(i));
                }
            } else if (input.matches("done\\s[1-9][0-9]?")) {
                int taskToComplete = Integer.valueOf(input.split(" ")[1]);
                inputs.get(taskToComplete - 1).markAsCompleted();
                System.out.println("Duke The Bear has marked this task as done!");
                System.out.println("Current List:");
                System.out.println("---------------");
                for (int i = 0; i < inputs.size(); i++) {
                    System.out.println( (i+1) + ": " + inputs.get(i));
                }
            } else {
                Task task = new Task(input);
                inputs.add(task);
                System.out.println(input + " has been added to the list!");
            }


        }
    }
}
