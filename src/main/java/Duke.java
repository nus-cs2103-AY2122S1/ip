import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
                /*String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo); */

        String greetingMsg = "Hello! I'm Duke\nWhat can I do for you?";
        String breakline = "____________________________________________________________";
        String exitCmd = "bye";
        String listCmd = "list";
        String cmd;
        String byeMsg = "Bye. Hope to see you again soon!";
        Scanner scanner = new Scanner(System.in);

        String[] tasks = new String[100];

        System.out.println(breakline);
        System.out.println(greetingMsg);
        System.out.println(breakline);

        int count = 0;
        do {
            cmd = scanner.nextLine();

            if(cmd.equals(exitCmd)) {
                System.out.println(byeMsg);
                System.out.println(breakline);
                break;
            } else if(cmd.equals(listCmd)) {
                for(int i=0;i < count; i++) {
                    System.out.printf("%d. %s\n", i+1, tasks[i]);
                }
                System.out.println(breakline);
            } else {
                tasks[count] = cmd;
                System.out.printf("added: %s\n", cmd);
                System.out.println(breakline);
                count++;
            }
        } while (!cmd.equals(exitCmd));

    }
}
