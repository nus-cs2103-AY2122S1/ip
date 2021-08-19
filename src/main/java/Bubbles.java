import java.util.Scanner;

public class Bubbles {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        String introduction = "You should do what you want to do!\n"
                + "Hello I'm Bubbles from the Powerpuff Girls, what are you up to?";

        System.out.println("Hello from\n" + logo + "\n");
        formatting(introduction);

        echoTask();
    }

    public static void echo() {
        Scanner sc = new Scanner(System.in);

        while (sc.hasNext()) {
            String input = sc.nextLine();

            if (input.equals("bye")) {
                String farewell = "Bye-bye! Hope to see you again soon!";

                int n = formatting(farewell);
                break;
            } else {
                int n = formatting(input);
            }
        }

        sc.close();
    }

    public static void echoTask() {
        Scanner sc = new Scanner(System.in);
        ToDoList toDoList = new ToDoList();

        while (sc.hasNext()) {
            String input = sc.nextLine();

            if (input.equals("bye")) {
                String farewell = "Bye-bye! Hope to see you again soon!";

                formatting(farewell);
                break;
            } else {
                toDoList.taskListener(input);
            }
        }
    }

    public static int formatting(String str) {
        String separator = "-----------------------------------------------------------------";

        System.out.println(separator + "\n"
                + str + "\n"
                + separator);

       return 0;
    }
}
