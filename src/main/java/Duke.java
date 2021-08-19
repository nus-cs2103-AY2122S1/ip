import java.util.Scanner;

public class Duke {
        public static void main (String[]args){
            Scanner in = new Scanner(System.in);
            ListOfTasks xs = new ListOfTasks();
            String logo = " ____        _        \n"
                    + "|  _ \\ _   _| | _____ \n"
                    + "| | | | | | | |/ / _ \\\n"
                    + "| |_| | |_| |   <  __/\n"
                    + "|____/ \\__,_|_|\\_\\___|\n";
            System.out.println("Hello from\n" + logo);
            System.out.println("____________________________________________________________");
            System.out.println("Hello! I'm Duke");
            System.out.println("What can I do for you?");
            System.out.println("____________________________________________________________");
            String command = in.nextLine();

            while (!command.equals("bye")) {
                System.out.println("    ____________________________________________________________");
                if (command.equals("list")) {
                    xs.listOut();
                } else if (command.contains("done")) {
                    xs.done(command);
                } else {
                    xs.add(command);
                }
                System.out.println("    ____________________________________________________________");
                command = in.nextLine();
            }

            System.out.println("    ____________________________________________________________");
            System.out.println("    Bye. Hope to see you again soon!");
            System.out.println("    ____________________________________________________________");
        }
}

