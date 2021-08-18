import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        echo();
    }

    public static void echo() {
        Scanner sc = new Scanner(System.in);

        String introduction = "you should do what you want to do!\n"
                        + "     hello I'm Bubbles, what are you up to?";

        System.out.println(formatting(introduction));

        while (sc.hasNext()) {
            String input = sc.nextLine();

            if (input.equals("bye")) {
                String farewell = "bye-bye! hope to see you again soon!";

                System.out.println(formatting(farewell));
                break;
            } else {
                System.out.println(formatting(input));
            }
        }
    }

    public static String formatting(String str) {
        String indentation = "     ";
        String separator = "----------------------------------------";

        String res = indentation + separator + "\n"
                + indentation + str + "\n"
                + indentation + separator + "\n";

       return res;
    }
}
