import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);

        String welcomeString = "____________________________________________________________\n"
                + "Yo! Duke here...on behalf of Yang Yuzhao.\n"
                + "What do ya want from me?\n"
                + "____________________________________________________________\n";
        String byeString = "____________________________________________________________\n"
                + "Duke out! Wake me up when ya need me again:)\n"
                + "____________________________________________________________\n";
        System.out.println(welcomeString);

        // ask for user input
        Scanner in = new Scanner(System.in);
        String nextLine = in.nextLine();
        ArrayList<Task> storeRoom = new ArrayList<>();
        Task task;

        // not bye
        while (!nextLine.equals("bye")) {
            // check list
            if (nextLine.equals("list")) {
                System.out.println("____________________________________________________________\n"
                        + "Here are the tasks in your list:");
                int counter = 1;
                for (Task taskForLoop : storeRoom) {
                    System.out.println(counter
                            + "."
                            + taskForLoop);
                    counter++;
                }
                System.out.println("____________________________________________________________\n");
                nextLine = in.nextLine();
                continue;
            }

            // finish task by index
            if (nextLine.startsWith("done")) {
                int intValue = Integer.parseInt(nextLine.replaceAll("[^0-9]", ""));
                Task doneTask = storeRoom.get(intValue - 1);
                doneTask.doneTask();
                storeRoom.set(intValue - 1, doneTask);
                System.out.println("____________________________________________________________\n"
                        + "Nice! I've marked this task as done:\n  "
                        + doneTask
                        + "\n"
                        + "____________________________________________________________\n");
                nextLine = in.nextLine();
                continue;
            }

            // add task
            if (nextLine.startsWith("todo")) {
                task = new ToDo(nextLine.substring(5));
            } else if (nextLine.startsWith("deadline")) {
                String[] splitDeadline = nextLine.split("/by ");
                String deadlineContent = splitDeadline[0].substring(9);
                String by = splitDeadline[1];
                task = new Deadline(deadlineContent, by);
            } else {
                String[] splitEvent = nextLine.split("/at ");
                String deadlineContent = splitEvent[0].substring(6);
                String at = splitEvent[1];
                task = new Event(deadlineContent, at);
            }

            storeRoom.add(task);
            System.out.println("____________________________________________________________\n"
                    + "Got it. I've added this task:\n  "
                    + task
                    + "\n"
                    + "Now you have "
                    + storeRoom.size()
                    + " tasks in the list."
                    + "\n"
                    + "____________________________________________________________\n");
            nextLine = in.nextLine();
        }

        // bye
        System.out.println(byeString);
        in.close();
    }
}
