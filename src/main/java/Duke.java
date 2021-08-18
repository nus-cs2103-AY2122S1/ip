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
                System.out.println("in");
                continue;

            }
                System.out.println("added: " + in);
                storedInfo[count] = new Task(in);
                count++;

            }


        System.out.println("Bye. Hope to see you again!");
    }
}
