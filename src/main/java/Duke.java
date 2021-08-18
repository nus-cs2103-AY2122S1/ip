import java.util.Scanner;
import java.util.ArrayList;

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
        ArrayList<Task> storedInfo = new ArrayList<Task>();
        //Task[] storedInfo = new Task[100];
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
                if (in.length() < 6) {
                    System.out.println("Invalid Input for done command");
                    continue;
                }
                int taskDone = parseInt(in.substring(5));
                if (taskDone > 100) {
                    System.out.println("Invalid Input for done command");
                    continue;
                }
                //System.out.println(taskDone);
                if (taskDone > count) {
                    System.out.println("Invalid Input for done command");
                    continue;
                }
                if (!storedInfo.get(taskDone - 1).isDone) {
                    System.out.println("Nice! I've marked this task as done:");
                    storedInfo.get(taskDone - 1).markAsDone();
                    System.out.println(storedInfo.get(taskDone - 1));
                    continue;
                } else {
                    System.out.println("This task is already marked as done");
                    continue;
                }

            }
            if (in.length() > 5 && in.substring(0,6).equals("delete")) {
                if (in.length() < 8) {
                    System.out.println("Invalid Input for delete command");
                    continue;
                }
                int taskDeleted = parseInt(in.substring(7));
                if (taskDeleted > 100) {
                    System.out.println("Invalid Input for delete command");
                    continue;
                }
                //System.out.println(taskDone);
                if (taskDeleted > count) {
                    System.out.println("Invalid Input for delete command");
                    continue;
                }
                System.out.println("Noted. I've removed this task:");
                System.out.println(storedInfo.get(taskDeleted - 1));
                storedInfo.remove(taskDeleted-1);
                continue;
            }
            if (in.length() > 3 && in.substring(0,4).equals("todo") ) {
                if (in.length() == 4) {
                    System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
                    continue;
                }
                System.out.println("Got it. I've added this task:");
                storedInfo.add(new ToDoTask(in.substring(4)));
                System.out.println(storedInfo.get(count));
                count++;
                if (count == 1) {
                    System.out.println("Now you have " + count + " task in the list.");
                } else {
                    System.out.println("Now you have " + count + " tasks in the list.");
                }
            } else if (in.length() > 4 && in.substring(0,5).equals("event")) {
                int i = in.indexOf("/");
                if (i < 0) {
                    System.out.println("Time not detected. Please try again");
                    continue;
                }
                System.out.println("Got it. I've added this task:");
                storedInfo.add(new EventTask(in.substring(6, i), in.substring(i + 1)));
                System.out.println(storedInfo.get(count));
                count++;
                if (count == 1) {
                    System.out.println("Now you have " + count + " task in the list.");
                } else {
                    System.out.println("Now you have " + count + " tasks in the list.");
                }
            } else if (in.length() > 7 && in.substring(0,8).equals("deadline")) {
                int i = in.indexOf("/");
                if (i < 0) {
                    System.out.println("Time not detected. Please try again");
                    continue;
                }
                System.out.println("Got it. I've added this task:");
                storedInfo.add(new DeadlineTask(in.substring(9, i), in.substring(i + 1)));
                System.out.println(storedInfo.get(count));
                count++;
                if (count == 1) {
                    System.out.println("Now you have " + count + " task in the list.");
                } else {
                    System.out.println("Now you have " + count + " tasks in the list.");
                }
            } else {
                System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }

            }


        System.out.println("Bye. Hope to see you again!");
    }
}
