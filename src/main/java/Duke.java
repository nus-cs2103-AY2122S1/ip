import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TaskList taskList = new TaskList();
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("    ____________________________________________________________\n"
                + "    Hello! I'm Duke \n" + "    What can I do for you?\n" +
                "    ____________________________________________________________\n");
        boolean end;
        end = true;
        while (end) {
            String text = scanner.nextLine();
            String[] splitText = text.trim().split("\\s+");
            System.out.println("    ____________________________________________________________\n");
            if (splitText[0].equals("list")) {
                taskList.listOut();
            } else if (splitText[0].equals("bye")) {
                end = false;
                System.out.println("    Bye. Hope to see you again soon!");
            } else {
                taskList.add(text);
            }
            System.out.println("    ____________________________________________________________\n");
        }
    }
}
