import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        boolean end = false;
        String[] taskList = new String[100];
        int itemCount = 0;

        System.out.println("Hello I'm Duke\n What can I do for you?");

        //while not ended, continue to wait for user's task inputs
        while(!end) {
            Scanner sc = new Scanner(System.in);
            String task = sc.nextLine();

            //if user enters "bye", end the loop
            if (task.equalsIgnoreCase("bye")) {
                end = !end;
                System.out.println("Bye. Hope to see you again soon!");

            // if user enters "list", list out all the tasks in the task list
            } else if (task.equalsIgnoreCase("list")) {
                for (int i = 0; i < itemCount; i++) {
                    System.out.println(i + 1 + ". " + taskList[i]);
                }
            // else display the added task back to the user
            } else {
                taskList[itemCount] = task;
                System.out.println("added: " + task);
                itemCount++;
            }
        }

    }
}
