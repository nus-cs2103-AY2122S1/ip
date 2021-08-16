import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Duke\n What can I do for you?");

        boolean stop = false;
        ArrayList<Task> inputArr = new ArrayList<Task>();


        while (!stop) {
            Scanner sc = new Scanner(System.in);
            String input = sc.nextLine();
            if (input.equals("list")) {
                System.out.println("Here are the tasks in your list: ");
                for (int i = 0; i < inputArr.size(); i++) {
                    Task currTask = inputArr.get(i);
                    System.out.println((i+1) + "." + "[" + currTask.getStatusIcon() + "] " + currTask.getDescription());
                }
            }
            else if (input.equals("bye")) {
                stop = true;
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }
            else if (input.startsWith("done")) {
                int number = Integer.parseInt(String.valueOf(input.charAt(5)));
                System.out.println("Nice! I've marked this task as done:");
                System.out.println("[X] " + inputArr.get(number-1).getDescription());
                inputArr.get(number - 1).markAsDone();
            }
            else {
                inputArr.add(new Task(input));
                System.out.println("added: " + input);
            }
        }
    }
}
