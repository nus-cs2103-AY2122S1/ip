import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String startText = "Hello! I'm Duke\n" + "What can I do for you?";
        System.out.println(startText);
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> savedInputs = new ArrayList<>();
        while (sc.hasNext()) {
            String command = sc.next();
            if (command.equals("bye")) {
                String closingMessage =  "Goodbye! Hope to see you again soon!\n";
                System.out.println(outputTemplate(closingMessage));
                sc.close();
                break;
            } else if (command.equals("list")) {
                String list = "These are the tasks in your list: \n";
                for (int i = 0; i < savedInputs.size(); i++) {
                    list += Integer.toString(i + 1) + ". " + savedInputs.get(i).toString() + "\n";
                }
                System.out.println(outputTemplate(list));
            } else if (command.equals("done")) {
                int taskNumber = sc.nextInt();
                Task completedTask = savedInputs.get(taskNumber - 1);
                completedTask.markAsDone();
                String message = "Good work! Task is now marked as done: \n" + completedTask.toString() + "\n";
                System.out.println(outputTemplate(message));
            } else {
                String description = command + sc.nextLine();
                savedInputs.add(new Task(description));
                System.out.println(outputTemplate(description + "\n"));
            }
        }
    }

    private static String outputTemplate(String output) {
        String line = "~ * ~ * ~ * ~ * ~ * ~ * ~ * ~ * ~ * ~ * ~ * ~ * ~ * ~ * ~ * ~ * ~";
        return line + "\n" + output + line;
    }
}
