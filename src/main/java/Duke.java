import java.util.*;

public class Duke {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        String logo = " _           _        \n"
                + "| |    _   _| | _____ \n"
                + "| |   | | | | |/ / _ \\\n"
                + "| |___| |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        String[] list = new String[100];
        boolean[] done = new boolean[100];
        // boolean[] td = new boolean[100];
        // boolean[] deadline = new boolean[100];
        // boolean[] event = new boolean[100];
        int amount = 0;

        String lineBreak = "\t____________________________________________________________";
        System.out.println(lineBreak
                + "\n\t Hello! I'm Luke, your slightly useful personal assistant!\n"
                + "\t What can I do for you, my liege?\n"
                + "\t Type 'list' to show previous inputs\n"
                + "\t Type 'todo TASK' to indicate that TASK has to be done\n"
                + "\t Type 'deadline TASK /by DATE/TIME' to indicate that TASK has to be done by DATE/TIME\n"
                + "\t Type 'event TASK /at DATE/TIME PERIOD' to indicate that TASK occurs at DATE/TIME PERIOD\n"
                + "\t Type 'done #' to indicate that task # has been done\n"
                + "\t Type 'bye' to end\n"
                + lineBreak);

        String response = scanner.nextLine();

        while (!response.equals("bye")) {
            if (response.equals("list")) {
                System.out.println(lineBreak);
                System.out.println("\t Here are the tasks in your list:");
                for (int i = 0; i < amount; i++) {
                    String doneI = " ";
                    if (done[i]) doneI = "X";
                    // String type = " ";
                    // if (td[i]) type = "T";
                    // if (deadline[i]) type = "D";
                    // if (event[i]) type = "E";
                    System.out.println("\t " + (i + 1) + "." + list[i]);
                }
                System.out.println(lineBreak);
            } else if (response.matches("done ([0-9]|[1-9][0-9])")) {
                int taskNo = Integer.parseInt(response.replaceAll("\\D", "")) - 1;
                System.out.println(lineBreak);
                System.out.println("\t Nice! I've marked this task as done:");
                list[taskNo] = list[taskNo].substring(0,4) + 'X' + list[taskNo].substring(5);
                System.out.println("\t " + list[taskNo]);
                System.out.println(lineBreak);
                done[taskNo] = true;
            } else if (response.matches("todo .+")) {
                System.out.println(lineBreak);
                System.out.println("\t Got it. I've added this task:");
                System.out.println("\t   [T][ ] " + response.substring(5));
                System.out.println("\t Now you have " + (amount + 1) + " tasks in the list.");
                System.out.println(lineBreak);
                list[amount] = "[T][ ] " + response.substring(5);
                // td[amount] = true;
                amount++;
            } else if (response.matches("deadline .+")) {
                System.out.println(lineBreak);
                System.out.println("\t Got it. I've added this task:");
                int pos = response.indexOf('/');
                list[amount] = "[D][ ] "
                        + response.substring(9, pos - 1)
                        + " (by: " + response.substring(response.indexOf('/') + 4) + ")";
                System.out.println("\t   " + list[amount]);
                System.out.println("\t Now you have " + (amount + 1) + " tasks in the list.");
                System.out.println(lineBreak);
                // deadline[amount] = true;
                amount++;
            } else if (response.matches("event .+")) {
                System.out.println(lineBreak);
                System.out.println("\t Got it. I've added this task:");
                int pos = response.indexOf('/');
                list[amount] = "[E][ ] "
                        + response.substring(6, pos - 1)
                        + " (at: " + response.substring(response.indexOf('/') + 4) + ")";
                System.out.println("\t   " + list[amount]);
                System.out.println("\t Now you have " + (amount + 1) + " tasks in the list.");
                System.out.println(lineBreak);
                // deadline[amount] = true;
                amount++;
            } else if (response.equals("todo")) {
                System.out.println(lineBreak);
                System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
                System.out.println(lineBreak);
            } else if (response.equals("deadline")) {
                System.out.println(lineBreak);
                System.out.println("☹ OOPS!!! The description of a deadline cannot be empty.");
                System.out.println(lineBreak);
            } else if (response.equals("event")) {
                System.out.println(lineBreak);
                System.out.println("☹ OOPS!!! The description of an event cannot be empty.");
                System.out.println(lineBreak);
            } else {
                System.out.println(lineBreak);
                System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                System.out.println(lineBreak);
            }
            response = scanner.nextLine();
        }

        System.out.println(lineBreak);
        System.out.println("\t Bye! Talk again sometime!");
        System.out.println(lineBreak);

        scanner.close();
    }
}