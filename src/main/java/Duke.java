import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        boolean end = false;
        Task[] taskList = new Task[100];
        int itemCount = 0;

        System.out.println("Hello I'm Duke\nWhat can I do for you?");

        //while not ended, continue to wait for user's task inputs
        while(!end) {
            int completedTask;
            Scanner sc = new Scanner(System.in);
        if (sc.hasNext("done")) {
                sc.next();
                completedTask = sc.nextInt() - 1;
                taskList[completedTask].markDone();
                System.out.println("\tNice! I've marked this task as done:");
                System.out.println(taskList[completedTask]);
                continue;
            }

            String taskDetails = sc.nextLine().trim();

            //if user enters "bye", end the loop
            if (taskDetails.equalsIgnoreCase("bye")) {
                end = !end;
                System.out.println("\tBye. Hope to see you again soon!");

            // if user enters "list", list out all the tasks in the task list
            } else if (taskDetails.equalsIgnoreCase("list")) {
                System.out.println("\tHere are the tasks in your list:");
                for (int i = 0; i < itemCount; i++) {
                    System.out.println("\t" + (i + 1) + ". " + taskList[i]);
                }

            // else display the added task back to the user
            } else {
                taskList[itemCount] = new Task(taskDetails);
                System.out.println("\tadded: " + taskDetails);
                itemCount++;
            }
        }

    }
}
