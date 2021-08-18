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
        TaskList taskList = new TaskList();

        while (sc.hasNextLine()) {
            String userInput = sc.nextLine();

            if (userInput.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (userInput.equals("list")) {
                //Print the list here
                System.out.println("Here are the tasks in your list");
                System.out.println(taskList);
            } else if (userInput.startsWith("done ") && Util.isInteger(userInput.substring(5))) {
                //Extract id of task
                int index = Integer.parseInt(userInput.substring(5)) - 1;
                //Mark the task as done
                taskList.markAsDone(index);
                //Print out confirmation message
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(taskList.getTask(index));
            } else {
                //Add userInput to taskList
                Task newTask = new Task(userInput);
                taskList.addTask(newTask);
                //Print out confirmation message
                System.out.println("added: " + newTask.getDescription());
            }
        }
        sc.close();
    }
}