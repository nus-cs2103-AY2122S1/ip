import java.util.Scanner;
import java.util.Arrays;

public class Duke {
    private static final Task[] list = new Task[100];
    private static int listNumber = 0;

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo + "What can I do for you?");
        Scanner s = new Scanner(System.in);
        String command = s.nextLine();
        while(!command.equals("bye")) {
            switch(command.split(" ")[0]) {
                case "list":
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < listNumber; i++) {
                        int num = i + 1;
                        if (list[i] != null) {
                            System.out.println(num + "." + list[i].getStatusIcon()+ list[i].description);
                        }
                    }
                    command = s.nextLine();
                    break;
                case "done":
                    int finished = Integer.parseInt(command.split(" ")[1]) - 1;
                    list[finished].markAsDone();
                    System.out.println("Nice! I've marked this task as done:\n" + "  " + list[finished].getStatusIcon()
                            + list[finished].description);
                    command = s.nextLine();
                    break;
                default:
                    System.out.println("added: " + command);
                    list[listNumber]= new Task(command);
                    listNumber += 1;
                    command = s.nextLine();
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
