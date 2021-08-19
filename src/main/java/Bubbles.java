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

        System.out.println("Hello from\n" + logo + "\n" + formatting(introduction));

        echoTask();
    }

    public static void echo() {
        Scanner sc = new Scanner(System.in);

        while (sc.hasNext()) {
            String input = sc.nextLine();

            if (input.equals("bye")) {
                String farewell = "Bye-bye! Hope to see you again soon!";

                System.out.println(formatting(farewell));
                break;
            } else {
                System.out.println(formatting(input));
            }
        }

        sc.close();
    }

    public static void echoTask() {
        Task toDoList = new Task();

        toDoList.taskListener();
    }

    public static String formatting(String str) {
        String separator = "-----------------------------------------------------------------";

        String res = separator + "\n"
                + str + "\n"
                + separator;

       return res;
    }
}
