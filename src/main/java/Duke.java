import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        Scanner input = new Scanner(System.in);
        Task[] storedInfo = new Task[100];
        int count = 0;
        while (input.hasNextLine()) {
            String in = input.nextLine();
            if (in.equals("bye") || in.equals("Bye")) {
                break;
            }
            if (in.equals("list") || in.equals("List")) {
                int counter = 1;
                System.out.println(" Here are the tasks in your list:");
                for (Task item: storedInfo) {

                    if(item != null) {
                        System.out.println(counter + ". " + item.toString());
                        counter++;
                    }

                }
                continue;
            }
            if (in.length() > 3 && in.substring(0,4).equals("done") ) {
                /*if (in.substring(4,5) != " ") {
                    System.out.println("Invalid input for done command");
                };*/
                int taskDone = parseInt(in.substring(5));
                if (taskDone > 100) {
                    System.out.println("Invalid Input for done command");
                    continue;
                }
                System.out.println(taskDone);
                if (storedInfo[taskDone-1] == null) {
                    System.out.println("Invalid Input for done command");
                    continue;
                }
                System.out.println("Nice! I've marked this task as done:");
                storedInfo[taskDone-1].markAsDone();
                System.out.println(storedInfo[taskDone-1]);
                //int i = parseInt(in);
                /*if (in.charAt(4).equals(e)) {
                    System.out.println("Invalid Input for done");
                    break;
                }*/
                continue;

            }
            if (in.length() > 3 && in.substring(0,4).equals("todo") ) {
                System.out.println("Got it. I've added this task:");
                storedInfo[count] = new ToDoTask(in.substring(4));
                System.out.println(storedInfo[count]);
                count++;
                if (count == 1) {
                    System.out.println("Now you have " + count + " task in the list.");
                } else {
                    System.out.println("Now you have " + count + " tasks in the list.");
                }
            } else if (in.length() > 4 && in.substring(0,5).equals("event")) {
                System.out.println("Got it. I've added this task:");
                int timeLocator = in.indexOf("/");
                String timeString = in.substring(timeLocator);
                storedInfo[count] = new EventTask(in.substring(6, timeLocator), in.substring(timeLocator+1));
                System.out.println(storedInfo[count]);
                count++;
                if (count == 1) {
                    System.out.println("Now you have " + count + " task in the list.");
                } else {
                    System.out.println("Now you have " + count + " tasks in the list.");
                }
            } else if (in.length() > 7 && in.substring(0,8).equals("deadline")) {
                System.out.println("Got it. I've added this task:");
                int timeLocator = in.indexOf("/");
                String timeString = in.substring(timeLocator);
                storedInfo[count] = new DeadlineTask(in.substring(9, timeLocator), in.substring(timeLocator+1));
                System.out.println(storedInfo[count]);
                count++;
                if (count == 1) {
                    System.out.println("Now you have " + count + " task in the list.");
                } else {
                    System.out.println("Now you have " + count + " tasks in the list.");
                }
            } else {
                System.out.println("Invalid task input");
            }

            }


        System.out.println("Bye. Hope to see you again!");
    }
}
