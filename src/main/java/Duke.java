import java.util.Scanner;



public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke. \n" + "What can I do for you?");



        Scanner scan = new Scanner(System.in);

        while (true) {
            String data = scan.nextLine();
            if (data.equals("bye")) {
                break;
            }
            printMsg(data);
        }

        printMsg("Bye. Hope to see you again soon!");

    }

    public static void printMsg(String msg) {
        System.out.println("-----------------------\n" +
                msg +
                "\n-----------------------\n");
    }


}
