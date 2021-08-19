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
        int amount = 0;

        String lineBreak = "\t____________________________________________________________";
        System.out.println(lineBreak
                + "\n\t Hello! I'm Luke, your slightly useful personal assistant!\n"
                + "\t What can I do for you, my liege?\n"
                + "\t Type 'list' to show previous inputs\n"
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
                    System.out.println("\t " + (i + 1) + ".[" + doneI + "] " + list[i]);
                }
                System.out.println(lineBreak);
            } else if (response.matches("done ([0-9]|[1-9][0-9])")) {
                int taskNo = Integer.parseInt(response.replaceAll("\\D", "")) - 1;
                System.out.println(lineBreak);
                System.out.println("\t Nice! I've marked this task as done:");
                System.out.println("\t   [X] " + list[taskNo]);
                System.out.println(lineBreak);
                done[taskNo] = true;
            } else {
                System.out.println(lineBreak
                        + "\n\t added: "
                        + response
                        + "\n"
                        + lineBreak);
                list[amount] = response;
                amount++;
            }
            response = scanner.nextLine();
        }

        System.out.println(lineBreak
                + "\n\t Bye! Talk again sometime!\n"
                + lineBreak);

        scanner.close();
    }
}