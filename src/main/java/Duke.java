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
                    System.out.println((i+1) + "." + currTask.toString());
                }
            }
            else if (input.equals("bye")) {
                stop = true;
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }
            else if (input.startsWith("done")) {
                int number = Integer.parseInt(String.valueOf(input.charAt(5)));
                inputArr.get(number - 1).markAsDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(inputArr.get(number-1).toString());
            }
            else {
                System.out.println("Got it. I have added this task: ");
                Task currTask;
                if (input.startsWith("todo")) {
                    currTask = new Todo(input.substring(5));
                }
                else if (input.startsWith("deadline")) {
                    int byIndex = input.indexOf("/");
                    String by = input.substring(byIndex+4);
                    currTask = new Deadline(input.substring(9, byIndex -1), by);
                }
                else {
                    int byIndex = input.indexOf("/");
                    String by = input.substring(byIndex+4);
                    currTask = new Event(input.substring(6, byIndex -1),by);
                }
                inputArr.add(currTask);
                System.out.println(currTask);
                System.out.println("Now you have " + inputArr.size() + " tasks in the list.");
            }
        }
    }
}
