import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        boolean end = false;
        Task[] taskList = new Task[100];
        int itemCount = 0;

        System.out.println("Hello I'm Duke\nWhat can I do for you?");
        Scanner sc = new Scanner(System.in);
        //while not ended, continue to wait for user's task inputs
        while(!end) {
            int completedTask;

            String commandWord = "";
            if(sc.hasNext()) {
                commandWord = sc.next().toLowerCase();
            }

            if (commandWord.equals("done")) {
                completedTask = sc.nextInt() - 1;
                taskList[completedTask].markDone();
                System.out.println("\tNice! I've marked this task as done:");
                System.out.println("\t  " + taskList[completedTask]);

            } else if (commandWord.equals("list")) {
                System.out.println("\tHere are the tasks in your list:");
                for (int i = 0; i < itemCount; i++) {
                    System.out.println("\t" + (i + 1) + ". " + taskList[i]);
                }

            } else if (commandWord.equals("bye")) {
                end = true;
                System.out.println("\tBye. Hope to see you again soon!");

            } else if (commandWord.equals("todo")) {
                String taskDetails = sc.nextLine();
                taskList[itemCount] = new ToDo(taskDetails);
                System.out.println("\tGot it. I've added this ToDo:\n" + "\t  " + taskList[itemCount]);
                itemCount++;
                System.out.println("\tNow you have " + itemCount + " tasks in the list.");

            } else if (commandWord.equals("deadline")) {
                String[] taskDetailsSplit = sc.nextLine().split("/by");
                taskList[itemCount] = new Deadline(taskDetailsSplit[0], taskDetailsSplit[1]);
                System.out.println("\tGot it. I've added this Deadline:\n" + "\t  " + taskList[itemCount]);
                itemCount++;
                System.out.println("\tNow you have " + itemCount + " tasks in the list.");

            } else if (commandWord.equals("event")) {
                String[] taskDetailsSplit = sc.nextLine().split("/at");
                taskList[itemCount] = new Event(taskDetailsSplit[0], taskDetailsSplit[1]);
                System.out.println("\tGot it. I've added this Event:\n" + "\t  " + taskList[itemCount]);
                itemCount++;
                System.out.println("\tNow you have " + itemCount + " tasks in the list.");

            }





//            String taskDetails = sc.nextLine().trim();
//
//            //if user enters "bye", end the loop
//            if (taskDetails.equalsIgnoreCase("bye")) {
//
//
//            // if user enters "list", list out all the tasks in the task list
//            } else if (taskDetails.equalsIgnoreCase("list")) {
//
//
//            // else display the added task back to the user
//            } else {
//                taskList[itemCount] = new Task(taskDetails);
//                System.out.println("\tadded: " + taskDetails);
//                itemCount++;
//            }
        }
        sc.close();

    }
}
