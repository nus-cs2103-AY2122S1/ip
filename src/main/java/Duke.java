import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Task> taskList = new ArrayList<>();

        String divider = "\n ------------------------------------------- \n";
        String indent = "       ";

        System.out.println(indent + "Hello! I'm Viper :)");
        System.out.println(indent + "How can I help you today?");
        System.out.println(divider);

        String str = sc.nextLine();

        while (!str.trim().equalsIgnoreCase("bye")) {
            //list out items in the list
            if (str.trim().equalsIgnoreCase("list")) {
                System.out.println(indent + "Here are the tasks in your list:");
                for (int i = 0; i < taskList.size(); i++) {
                    int currIndex = i + 1;
                    Task currTask = taskList.get(i);
                    System.out.println(indent + currIndex + ". [" + currTask.getStatusIcon() + "] "
                            + currTask.description);
                }
                System.out.println(divider);
            }
            //mark numbered task as done
            else if (str.toLowerCase().contains("done")) {
                int taskNo = Integer.parseInt(str.substring(5)) - 1;
                //check if taskNo exists:
                if (taskNo < taskList.size()) {
                    Task currTask = taskList.get(taskNo);
                    currTask.isDone = true;
                    System.out.println(indent + "Good job on completing your task!");
                    System.out.println(indent + "I've marked this task as done:");
                    System.out.println(indent + "[" + currTask.getStatusIcon() + "] " + currTask.description);
                }
                else {
                    System.out.println(indent + "Sorry, this task does not exist. :(");
                }
                System.out.println(divider);

            }
            else {
                Task newTask = new Task(str);
                taskList.add(newTask);
                System.out.println(indent + "added: " + str);
                System.out.println(divider);
            }

            str = sc.nextLine();
        }

        System.out.println(indent + "Bye! See you again~");
        System.out.println(divider);

    }
}
