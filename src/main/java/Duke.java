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
                + "Bye bye! Wake me up when ya need me again:)\n"
                + "____________________________________________________________\n";
        System.out.println(welcomeString);

        // ask for user input
        Scanner in = new Scanner(System.in);
        String nextLine = in.nextLine();
        ArrayList<Task> storeRoom = new ArrayList<>();

        while (!nextLine.equals("bye")) {
            if (nextLine.equals("list")) {
                System.out.println("____________________________________________________________\n"
                        + "Here are the tasks in your list:\n");
                int counter = 1;
                for (Task task : storeRoom) {
                    System.out.println(counter
                            + "."
                            + task.getStatusIcon()
                            + " "
                            + task.getDescription());
                    counter++;
                }
                System.out.println("____________________________________________________________\n");
                nextLine = in.nextLine();
                continue;
            }
            if (nextLine.contains("done")) {
                System.out.print("____________________________________________________________\n"
                        + "Nice! I've marked this task as done: \n");
                int intValue = Integer.parseInt(nextLine.replaceAll("[^0-9]", ""));
                Task doneTask = storeRoom.get(intValue - 1).doneTask();
                storeRoom.set(intValue - 1, doneTask);
                System.out.println(doneTask.getStatusIcon()
                        + " "
                        + doneTask.getDescription()
                        + "\n"
                        + "____________________________________________________________\n");
                nextLine = in.nextLine();
                continue;
            }
            String nextToPrint = "____________________________________________________________\n"
                    + "added: "
                    + nextLine;
            System.out.println(nextToPrint);
            System.out.println("____________________________________________________________\n");
            storeRoom.add(new Task(nextLine));
            nextLine = in.nextLine();
        }

        System.out.println(byeString);
        in.close();
    }
}
