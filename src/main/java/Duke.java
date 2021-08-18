import java.util.Scanner;

public class Duke {
    public static void printString() {
        Scanner sc = new Scanner(System.in);
        String inputString = sc.next();
        if(inputString.equals("bye")) {
            System.out.println("____________________________________________________________ \n" +
                    "Bye. Hope to see you again soon! \n" +
                    "____________________________________________________________");
        } else {
            System.out.println("____________________________________________________________ \n" +
                    inputString + "\n" +
                    "____________________________________________________________");
            printString();
        }
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm \n" + logo);
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");
        printString();
    }
}
