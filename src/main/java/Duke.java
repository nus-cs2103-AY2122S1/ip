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
        ArrayList<Task> taskList = new ArrayList<>();

        while (sc.hasNextLine()) {
            String userInput = sc.nextLine();

            if (userInput.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (userInput.equals("list")) {
                //Print the list here
                System.out.println("Here are the tasks in your list");
                for (int i = 0; i < taskList.size(); i += 1) {
                    System.out.println((i + 1) + "." + taskList.get(i));
                }
            } else if (userInput.startsWith("done ") && Util.isInteger(userInput.substring(5))) {
                //Extract id of task
                int index = Integer.parseInt(userInput.substring(5)) - 1;
                //Mark the task as done
                taskList.get(index).markAsDone();
                //Print out confirmation message
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(taskList.get(index));
            } else {
                //Add userInput to taskList
                Task newTask = new Task(userInput);
                taskList.add(newTask);
                //Print out confirmation message
                System.out.println("added: " + newTask.getDescription());
            }
        }
        sc.close();
    }
}